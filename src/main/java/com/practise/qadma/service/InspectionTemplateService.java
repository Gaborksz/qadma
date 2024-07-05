package com.practise.qadma.service;

import com.practise.qadma.payload.inspectiontemplate.InspectionTemplateDTO;

public interface InspectionTemplateService {

    InspectionTemplateDTO findById(long id);

    InspectionTemplateDTO save(InspectionTemplateDTO templateDTO);

}
