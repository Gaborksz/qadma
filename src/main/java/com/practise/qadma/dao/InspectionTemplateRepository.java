package com.practise.qadma.dao;

import com.practise.qadma.entity.InspectionTemplate;

import java.util.Optional;

public interface InspectionTemplateRepository {

        Optional<InspectionTemplate> findById(long id);

        InspectionTemplate save(InspectionTemplate inspectionTemplate);

        InspectionTemplate update(InspectionTemplate inspectionTemplate);
}
