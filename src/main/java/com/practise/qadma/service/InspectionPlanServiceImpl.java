package com.practise.qadma.service;

import com.practise.qadma.dao.InspectionPlanRepository;
import com.practise.qadma.dao.InspectionTemplateRepository;
import com.practise.qadma.entity.InspectionPlan;
import com.practise.qadma.entity.InspectionTemplate;
import com.practise.qadma.exception.ItemNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class InspectionPlanServiceImpl implements InspectionPlanService {

    private InspectionPlanRepository inspectionPlanRepository;
    private InspectionTemplateRepository inspectionTemplateRepository;
    private SequenceService sequenceService;

    @Autowired
    public InspectionPlanServiceImpl(InspectionPlanRepository inspectionPlanRepository, InspectionTemplateRepository inspectionTemplateRepository, SequenceService sequenceService) {
        this.inspectionPlanRepository = inspectionPlanRepository;
        this.inspectionTemplateRepository = inspectionTemplateRepository;
        this.sequenceService = sequenceService;
    }

    @Override
    public InspectionPlan findById(long id) throws ItemNotFoundException {

        return inspectionPlanRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id, "InspectionPlan"));
    }


    @Override
    public InspectionPlan findByProductId(long productId) {
        return inspectionPlanRepository.finByProductId(productId);
    }

    @Transactional
    @Override
    public InspectionPlan save(InspectionPlan inspectionPlan) {

//        TODO
//        Map<Integer, InspectionTemplate> managedSequence =
//                sequenceService.saveSequence(inspectionPlan.getTemplateSequence());
//
//        inspectionPlan.setTemplateSequence(managedSequence);

        inspectionPlanRepository.save(inspectionPlan);

        return inspectionPlan;
    }


    @Transactional
    @Override
    public InspectionPlan update(InspectionPlan inspectionPlan) {
        return inspectionPlanRepository.update(inspectionPlan);
    }


}
