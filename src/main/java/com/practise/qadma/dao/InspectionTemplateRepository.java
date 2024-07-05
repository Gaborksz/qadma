package com.practise.qadma.dao;

import com.practise.qadma.entity.InspectionTemplate;

import java.util.Optional;

public interface InspectionTemplateRepository {

        Optional<InspectionTemplate> findById(long id);

        InspectionTemplate saveNewOrGetExisting(InspectionTemplate inspectionTemplate);
}
