package com.practise.qadma.service;

import com.practise.qadma.dao.InspectionPlanRepository;
import com.practise.qadma.dao.InspectionTemplateRepository;
import com.practise.qadma.entity.InspectionPlan;
import com.practise.qadma.entity.InspectionTemplate;
import com.practise.qadma.exception.ItemNotFoundException;
import com.practise.qadma.payload.inspectionplan.InspectionPlanDTO;
import com.practise.qadma.payload.inspectiontemplate.InspectionTemplateDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class InspectionPlanServiceImpl implements InspectionPlanService {

    private InspectionPlanRepository inspectionPlanRepository;
    private InspectionTemplateRepository inspectionTemplateRepository;
    private ModelMapper modelMapper;

    @Autowired
    public InspectionPlanServiceImpl(InspectionPlanRepository inspectionPlanRepository, InspectionTemplateRepository inspectionTemplateRepository, ModelMapper modelMapper) {
        this.inspectionPlanRepository = inspectionPlanRepository;
        this.inspectionTemplateRepository = inspectionTemplateRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public InspectionPlanDTO findById(long id) throws ItemNotFoundException {

        InspectionPlan inspectionPlan = inspectionPlanRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id, "InspectionPlan"));

        return modelMapper.map(inspectionPlan, InspectionPlanDTO.class);
    }

    @Transactional
    @Override
    public InspectionPlanDTO save(InspectionPlanDTO newPlanDTO) {

        InspectionPlan newPlan = modelMapper.map(newPlanDTO, InspectionPlan.class);

        Map<Integer, InspectionTemplate> managedSequence = newPlan.getTemplateSequence()
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> inspectionTemplateRepository.saveNewOrGetExisting(entry.getValue())));

        newPlan.setTemplateSequence(managedSequence);

        inspectionPlanRepository.save(newPlan);

        return modelMapper.map(newPlan, InspectionPlanDTO.class);
    }


    @Transactional
    @Override
    public InspectionPlanDTO update(InspectionPlanDTO updatedPlanDTO) {

        InspectionPlan updatedPlan = modelMapper.map(updatedPlanDTO, InspectionPlan.class);

        InspectionPlan currentPlan = inspectionPlanRepository.findById(updatedPlanDTO.getId()).orElseThrow(() -> new ItemNotFoundException(updatedPlanDTO.getId(), "InspectionPlan"));

        updatedPlan.getTemplateSequence().forEach((sequenceNumber, updatedTemplate) -> inspectionTemplateRepository.saveNewOrGetExisting(updatedTemplate));

        currentPlan.setRevision(updatedPlanDTO.getRevision());
        currentPlan.setDateModified(updatedPlanDTO.getDateModified());
        currentPlan.setModifiedBy(updatedPlanDTO.getModifiedBy());
        currentPlan.setStatus(updatedPlanDTO.getStatus());
        currentPlan.setTemplateSequence(mapSequenceFromDTO(updatedPlanDTO.getTemplateSequence()));

        inspectionPlanRepository.update(currentPlan);

        return modelMapper.map(currentPlan, InspectionPlanDTO.class);
    }


    private Map<Integer, InspectionTemplate> mapSequenceFromDTO(Map<Integer, InspectionTemplateDTO> sequenceDto) {

        java.lang.reflect.Type entityMap = new TypeToken<HashMap<Integer, InspectionTemplate>>() {}.getType();
        return modelMapper.map(sequenceDto, entityMap);
    }
}
