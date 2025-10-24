package com.practise.qadma.service;

import com.practise.qadma.entity.InspectionTemplate;
import com.practise.qadma.payload.InspectionTemplateDTO;

public interface InspectionTemplateService {

    InspectionTemplate findById(long id);

    InspectionTemplateDTO save(InspectionTemplateDTO template);

    InspectionTemplate update(InspectionTemplate inspectionTemplate);
}
