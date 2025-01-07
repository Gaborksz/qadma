package com.practise.qadma.mappingservice;

import com.practise.qadma.entity.InspectionTemplate;
import com.practise.qadma.payload.InspectionTemplateDTO;
import com.practise.qadma.payload.view.InspectionTemplateViewDTO;
import com.practise.qadma.service.InspectionTemplateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InspectionTemplateMappingServiceImpl implements InspectionTemplateMappingService {

    private ModelMapper modelMapper;

    private InspectionTemplateService inspectionTemplateService;

    @Autowired
    public InspectionTemplateMappingServiceImpl(InspectionTemplateService inspectionTemplateService, ModelMapper modelMapper) {
        this.inspectionTemplateService = inspectionTemplateService;
        this.modelMapper = modelMapper;
    }

    @Override
    public InspectionTemplateViewDTO findById(long id) {
        return modelMapper.map(inspectionTemplateService.findById(id), InspectionTemplateViewDTO.class);
    }

    @Override
    public InspectionTemplateDTO save(InspectionTemplateDTO templateDTO) {

        InspectionTemplate newTemplate = modelMapper.map(templateDTO, InspectionTemplate.class);

        InspectionTemplate mangedTemplate = inspectionTemplateService.save(newTemplate);

        return modelMapper.map(mangedTemplate, InspectionTemplateDTO.class);
    }
}
