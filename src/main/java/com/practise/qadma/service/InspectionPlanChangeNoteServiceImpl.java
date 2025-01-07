package com.practise.qadma.service;

import com.practise.qadma.dao.InspectionPlanChangeNoteRepository;
import com.practise.qadma.entity.InspectionPlanChangeNote;
import com.practise.qadma.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InspectionPlanChangeNoteServiceImpl implements InspectionPlanChangeNoteService {

    private InspectionPlanChangeNoteRepository inspectionPlanChangeNoteRepository;


    @Autowired
    public InspectionPlanChangeNoteServiceImpl(InspectionPlanChangeNoteRepository inspectionPlanChangeNoteRepository) {
        this.inspectionPlanChangeNoteRepository = inspectionPlanChangeNoteRepository;
    }

    @Override
    public InspectionPlanChangeNote findById(long id) {

        Optional<InspectionPlanChangeNote> inspectionPlanChangeNote = inspectionPlanChangeNoteRepository.findById(id);

        if (inspectionPlanChangeNote.isEmpty()) throw new ItemNotFoundException(id, "InspectionPlanChangeNote");

        return inspectionPlanChangeNote.get();
    }

    @Override
    public InspectionPlanChangeNote save(InspectionPlanChangeNote changeNote) {
        return inspectionPlanChangeNoteRepository.save(changeNote);
    }
}
