package com.practise.qadma.conversion;

import com.practise.qadma.payload.InspectionTemplateDTO;
import com.practise.qadma.payload.view.InspectionTemplateViewDTO;

public interface InspectionTemplateConversionService {

    InspectionTemplateViewDTO findById(long id);

    InspectionTemplateDTO save(InspectionTemplateDTO templateDTO);
}
