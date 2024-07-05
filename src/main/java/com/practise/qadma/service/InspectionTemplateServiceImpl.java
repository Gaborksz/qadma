package com.practise.qadma.service;

import com.practise.qadma.dao.InspectionTemplateRepository;
import com.practise.qadma.entity.InspectionTemplate;
import com.practise.qadma.payload.inspectiontemplate.InspectionTemplateDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InspectionTemplateServiceImpl implements InspectionTemplateService {

    private InspectionTemplateRepository inspectionTemplateRepository;
    private ModelMapper modelMapper;

    @Autowired
    public InspectionTemplateServiceImpl(InspectionTemplateRepository inspectionTemplateRepository, ModelMapper modelMapper) {
        this.inspectionTemplateRepository = inspectionTemplateRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public InspectionTemplateDTO findById(long id) {
        return modelMapper.map(inspectionTemplateRepository.findById(id), InspectionTemplateDTO.class);
    }

    @Override
    public InspectionTemplateDTO save(InspectionTemplateDTO templateDTO) {

        Optional<InspectionTemplate> managedTemplate = inspectionTemplateRepository.findById(templateDTO.getId());

        if (managedTemplate.isPresent()) return templateDTO;

        InspectionTemplate savedTemplate = inspectionTemplateRepository.saveNewOrGetExisting(modelMapper.map(templateDTO, InspectionTemplate.class));

        return modelMapper.map(savedTemplate, InspectionTemplateDTO.class);
    }
}
