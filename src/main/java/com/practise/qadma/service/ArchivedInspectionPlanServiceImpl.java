package com.practise.qadma.service;

import com.practise.qadma.entity.ArchivedInspectionPlan;
import com.practise.qadma.entity.ArchivedInspectionTemplate;
import com.practise.qadma.entity.InspectionPlan;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ArchivedInspectionPlanServiceImpl implements ArchivedInspectionPlanService {

    private ArchivedInspectionTemplateService archivedInspectionTemplateService;

    @Autowired
    public ArchivedInspectionPlanServiceImpl(ArchivedInspectionTemplateService archivedInspectionTemplateService) {
        this.archivedInspectionTemplateService = archivedInspectionTemplateService;
    }

    @Transactional
    @Override
    public ArchivedInspectionPlan archiveInspectionPlan(InspectionPlan inspectionPlan) {

        ArchivedInspectionPlan archivedInspectionPlan = new ArchivedInspectionPlan();
        archivedInspectionPlan.setId(0);
        archivedInspectionPlan.setRevision(inspectionPlan.getRevision());
        archivedInspectionPlan.setDateCreated(inspectionPlan.getDateModified());
        archivedInspectionPlan.setCreatedBy(inspectionPlan.getModifiedBy());

        Map<Integer, ArchivedInspectionTemplate> archiveTemplateSequence = inspectionPlan.getTemplateSequence()
                .entrySet().stream().collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> archivedInspectionTemplateService.getLatestArchiveForInspectionTemplate(entry.getValue().getId()))
                );

        archivedInspectionPlan.setTemplateSequence(archiveTemplateSequence);

        return archivedInspectionPlan;
    }
}
