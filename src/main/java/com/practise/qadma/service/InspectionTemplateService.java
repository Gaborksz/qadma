package com.practise.qadma.service;

import com.practise.qadma.entity.InspectionTemplate;

public interface InspectionTemplateService {

    InspectionTemplate findById(long id);

    InspectionTemplate save(InspectionTemplate template);

    InspectionTemplate update(InspectionTemplate inspectionTemplate);
}
