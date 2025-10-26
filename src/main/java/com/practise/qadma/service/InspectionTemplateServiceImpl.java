package com.practise.qadma.service;

import com.practise.qadma.auth.entity.QadmaUser;
import com.practise.qadma.auth.service.QadmaUserService;
import com.practise.qadma.dao.InspectionTemplateRepository;
import com.practise.qadma.entity.InspectionTemplate;
import com.practise.qadma.exception.ItemNotFoundException;
import com.practise.qadma.payload.InspectionTemplateDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InspectionTemplateServiceImpl implements InspectionTemplateService {

    private final InspectionTemplateRepository inspectionTemplateRepository;
    private final QadmaUserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public InspectionTemplateServiceImpl(InspectionTemplateRepository inspectionTemplateRepository, QadmaUserService userService, ModelMapper modelMapper) {
        this.inspectionTemplateRepository = inspectionTemplateRepository;
        this.userService = userService;

        this.modelMapper = modelMapper;
    }

    @Override
    public InspectionTemplate findById(long id) {

        return inspectionTemplateRepository.findById(id)
                .orElseThrow(()-> new ItemNotFoundException(id,InspectionTemplate.class.getName()));
    }

    @Transactional
    @Override
    public InspectionTemplateDTO save(InspectionTemplateDTO templateDTO) {

        InspectionTemplate template =  modelMapper.map(templateDTO, InspectionTemplate.class);
        template = inspectionTemplateRepository.save(template);

        Set<InspectionTemplate> templates = new HashSet<>();
        templates.add(template);

        populateInspectionTemplatesWithUserObjects(templates);

        return  modelMapper.map(template, InspectionTemplateDTO.class);
    }

    @Transactional
    @Override
    public InspectionTemplate update(InspectionTemplate inspectionTemplate) {
        return inspectionTemplateRepository.update(inspectionTemplate);
    }

    private  void populateInspectionTemplatesWithUserObjects(Set<InspectionTemplate> templates) {

        Set<Long>  creatorIds = templates.stream().map(InspectionTemplate::getCreatorId).collect(Collectors.toSet());
        Set<Long>  modifierIds = templates.stream().map(InspectionTemplate::getModifierId).collect(Collectors.toSet());

        Set<Long> allUserIds = new HashSet<>(creatorIds);
        allUserIds.addAll(modifierIds);

        Set<QadmaUser> users = userService.findUsersByIds(allUserIds);
        Map<Long, QadmaUser> userMap = users.stream().
                collect(Collectors.toMap(QadmaUser::getId, user -> user));

         templates.forEach(template-> {
             template.setCreatedBy(userMap.get(template.getCreatorId()));
             template.setModifiedBy(userMap.get(template.getModifierId()));
         });
    }
}
