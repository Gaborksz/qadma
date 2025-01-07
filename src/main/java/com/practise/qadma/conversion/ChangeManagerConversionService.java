package com.practise.qadma.conversion;

import com.practise.qadma.payload.InspectionPlanChangeNoteDTO;
import com.practise.qadma.payload.ProductChangeNoteDTO;
import com.practise.qadma.payload.view.InspectionPlanChangeNoteViewDTO;
import com.practise.qadma.payload.view.ProductChangeNoteViewDTO;

import java.util.List;

public interface ChangeManagerConversionService {

    List<InspectionPlanChangeNoteViewDTO> processInspectionPlanChangeNote(InspectionPlanChangeNoteDTO inspectionPlanChangeNoteDTO);

    List<ProductChangeNoteViewDTO> processProductChangeNote(ProductChangeNoteDTO productChangeNoteDTO);

    ProductChangeNoteViewDTO getProductChangeNote(long id);
}
