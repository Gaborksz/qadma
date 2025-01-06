package com.practise.qadma.service;

import com.practise.qadma.dao.InspectionTemplateRepository;
import com.practise.qadma.entity.InspectionTemplate;
import com.practise.qadma.exception.ItemNotFoundException;
import com.practise.qadma.payload.InspectionTemplateDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InspectionTemplateServiceImpl implements InspectionTemplateService {

    private InspectionTemplateRepository inspectionTemplateRepository;

    @Autowired
    public InspectionTemplateServiceImpl(InspectionTemplateRepository inspectionTemplateRepository) {
        this.inspectionTemplateRepository = inspectionTemplateRepository;

    }

    @Override
    public InspectionTemplate findById(long id) {

        return inspectionTemplateRepository.findById(id)
                .orElseThrow(()-> new ItemNotFoundException(id,InspectionTemplate.class.getName()));
    }

    @Transactional
    @Override
    public InspectionTemplate save(InspectionTemplate template) {

        return inspectionTemplateRepository.save(template);
    }

    @Transactional
    @Override
    public InspectionTemplate update(InspectionTemplate inspectionTemplate) {
        return inspectionTemplateRepository.update(inspectionTemplate);
    }
}
