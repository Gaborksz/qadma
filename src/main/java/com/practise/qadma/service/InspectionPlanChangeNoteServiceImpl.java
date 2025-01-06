package com.practise.qadma.service;

import com.practise.qadma.dao.InspectionPlanChangeNoteRepository;
import com.practise.qadma.entity.InspectionPlanChangeNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InspectionPlanChangeNoteServiceImpl implements InspectionPlanChangeNoteService {

    private InspectionPlanChangeNoteRepository inspectionPlanChangeNoteRepository;


    @Autowired
    public InspectionPlanChangeNoteServiceImpl(InspectionPlanChangeNoteRepository inspectionPlanChangeNoteRepository) {
        this.inspectionPlanChangeNoteRepository = inspectionPlanChangeNoteRepository;
    }

    @Override
    public InspectionPlanChangeNote findById(long id) {
        return inspectionPlanChangeNoteRepository.findById(id);
    }

    @Override
    public InspectionPlanChangeNote save(InspectionPlanChangeNote changeNote) {
        return inspectionPlanChangeNoteRepository.save(changeNote);
    }
}
