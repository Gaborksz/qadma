package com.practise.qadma.service;

import com.practise.qadma.dao.ArchivedInspectionPlanRepository;
import com.practise.qadma.entity.ArchivedInspectionPlan;
import com.practise.qadma.entity.InspectionPlan;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchivedInspectionPlanServiceImpl implements ArchivedInspectionPlanService{

    @Autowired
    private ArchivedInspectionPlanRepository archivedInspectionPlanRepository;
    private ModelMapper modelMapper;

    public ArchivedInspectionPlanServiceImpl(ArchivedInspectionPlanRepository archivedInspectionPlanRepository, ModelMapper modelMapper) {
        this.archivedInspectionPlanRepository = archivedInspectionPlanRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ArchivedInspectionPlan save(InspectionPlan inspectionPlan) {

        ArchivedInspectionPlan archivedInspectionPlan = modelMapper.map(inspectionPlan, ArchivedInspectionPlan.class);
        archivedInspectionPlan.setId(0);
        archivedInspectionPlan.getTemplateSequence().entrySet().forEach(e-> e.getValue().setId(0));

        return archivedInspectionPlanRepository.save(archivedInspectionPlan);
    }
}
