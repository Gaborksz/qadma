package com.practise.qadma.service;

import com.practise.qadma.dao.ArchivedInspectionTemplateRepository;
import com.practise.qadma.entity.ArchivedInspectionTemplate;
import com.practise.qadma.entity.InspectionTemplate;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ArchivedInspectionTemplateServiceImpl implements ArchivedInspectionTemplateService{

    private ArchivedInspectionTemplateRepository archivedInspectionTemplateRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ArchivedInspectionTemplateServiceImpl(ArchivedInspectionTemplateRepository archivedInspectionTemplateRepository, ModelMapper modelMapper) {
        this.archivedInspectionTemplateRepository = archivedInspectionTemplateRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public ArchivedInspectionTemplate archiveInspectionTemplate(InspectionTemplate inspectionTemplate) {

        ArchivedInspectionTemplate archivedTemplate = modelMapper.map(inspectionTemplate, ArchivedInspectionTemplate.class);

        archivedTemplate.setDateCreated(inspectionTemplate.getDateModified());
        archivedTemplate.setId(0);

        ArchivedInspectionTemplate archivedInspectionTemplate = archivedInspectionTemplateRepository.archiveTemplate(archivedTemplate);
        return archivedInspectionTemplate;
    }


    @Override
    public ArchivedInspectionTemplate getLatestArchiveForInspectionTemplate(long id) {

        return archivedInspectionTemplateRepository.getLatestArchiveForInspectionTemplate(id);
    }
}
