package com.practise.qadma.mappingservice;

import com.practise.qadma.payload.InspectionTemplateDTO;
import com.practise.qadma.payload.view.InspectionTemplateViewDTO;

public interface InspectionTemplateMappingService {

    InspectionTemplateViewDTO findById(long id);

    InspectionTemplateDTO save(InspectionTemplateDTO templateDTO);
}
