package com.practise.qadma.mappingservice;

import com.practise.qadma.entity.InspectionPlanChangeNote;
import com.practise.qadma.entity.Product;
import com.practise.qadma.entity.ProductChangeNote;
import com.practise.qadma.payload.InspectionPlanChangeNoteDTO;
import com.practise.qadma.payload.ProductChangeNoteDTO;
import com.practise.qadma.payload.view.InspectionPlanChangeNoteViewDTO;
import com.practise.qadma.payload.view.ProductChangeNoteViewDTO;
import com.practise.qadma.supportclasses.InspectionPlanChangeManager;
import com.practise.qadma.supportclasses.ProductChangeManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChangeManagerMappingServiceImpl implements ChangeManagerMappingService {

//    private final ModelMapper modelMapper;
//    private final InspectionPlanChangeManager inspectionPlanChangeManager;
//    private final ProductChangeManager productChangeManager;
//
//
//    @Autowired
//    public ChangeManagerMappingServiceImpl(ModelMapper modelMapper, InspectionPlanChangeManager inspectionPlanChangeManager, ProductChangeManager productChangeManager) {
//        this.modelMapper = modelMapper;
//        this.inspectionPlanChangeManager = inspectionPlanChangeManager;
//        this.productChangeManager = productChangeManager;
//
//    }
//
//    @Override
//    public List<InspectionPlanChangeNoteViewDTO> processInspectionPlanChangeNote(InspectionPlanChangeNoteDTO inspectionPlanChangeNoteDTO) {
//
//        InspectionPlanChangeNote changeNote = modelMapper.map(inspectionPlanChangeNoteDTO, InspectionPlanChangeNote.class);
//
//        Map<Product, InspectionPlanChangeNote> processedChangeNotes = inspectionPlanChangeManager.processPlanChangeNote(null, changeNote, null);
//
//        return processedChangeNotes.values().stream()
//                .map(inspectionPlanChangeNote -> modelMapper.map(inspectionPlanChangeNote, InspectionPlanChangeNoteViewDTO.class))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<ProductChangeNoteViewDTO> processProductChangeNote(ProductChangeNoteDTO productChangeNoteDTO) {
//
//        ProductChangeNote productChangeNote = modelMapper.map(productChangeNoteDTO, ProductChangeNote.class);
//
//        List<ProductChangeNote> processedProductChangeNotes = productChangeManager.processProductChangeNote(productChangeNote);
//
//        return processedProductChangeNotes.stream().map(changeNote -> modelMapper.map(changeNote, ProductChangeNoteViewDTO.class)).toList();
//    }
}
