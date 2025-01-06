package com.practise.qadma.dao;

import com.practise.qadma.entity.InspectionTemplate;
import com.practise.qadma.entity.Product;

import java.util.Optional;
import java.util.Set;

public interface InspectionTemplateRepository {

        Optional<InspectionTemplate> findById(long id);

        InspectionTemplate save(InspectionTemplate inspectionTemplate);

        InspectionTemplate update(InspectionTemplate inspectionTemplate);
}
