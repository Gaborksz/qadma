package com.practise.qadma.conversion;

import com.practise.qadma.entity.InspectionPlanChangeNote;
import com.practise.qadma.payload.view.InspectionPlanChangeNoteViewDTO;
import com.practise.qadma.service.InspectionPlanChangeNoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InspectionPlanChangeNoteConversionServiceImpl implements InspectionPlanChangeNoteConversionService {

    private ModelMapper modelMapper;
    private InspectionPlanChangeNoteService inspectionPlanChangeNoteService;

    @Autowired
    public InspectionPlanChangeNoteConversionServiceImpl(ModelMapper modelMapper, InspectionPlanChangeNoteService inspectionPlanChangeNoteService) {
        this.modelMapper = modelMapper;
        this.inspectionPlanChangeNoteService = inspectionPlanChangeNoteService;
    }

    @Override
    public InspectionPlanChangeNoteViewDTO findById(long id) {

        InspectionPlanChangeNote changeNote = inspectionPlanChangeNoteService.findById(id);

        return modelMapper.map(changeNote, InspectionPlanChangeNoteViewDTO.class);
    }
}
