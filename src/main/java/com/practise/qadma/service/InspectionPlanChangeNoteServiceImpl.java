package com.practise.qadma.service;

import com.practise.qadma.dao.InspectionPlanChangeNoteRepository;
import com.practise.qadma.entity.ArchivedInspectionPlan;
import com.practise.qadma.entity.InspectionPlan;
import com.practise.qadma.entity.InspectionPlanChangeNote;
import com.practise.qadma.payload.changenote.InspectionPlanChangeNoteDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InspectionPlanChangeNoteServiceImpl implements InspectionPlanChangeNoteService {

    private InspectionPlanChangeNoteRepository inspectionPlanChangeNoteRepository;
    private InspectionPlanService inspectionPlanService;
    private ArchivedInspectionPlanService archivedInspectionPlanService;
    private ModelMapper modelMapper;

    @Autowired
    public InspectionPlanChangeNoteServiceImpl(InspectionPlanChangeNoteRepository inspectionPlanChangeNoteRepository, ArchivedInspectionPlanService archivedInspectionPlanService, InspectionPlanService inspectionPlanService, ModelMapper modelMapper) {
        this.inspectionPlanChangeNoteRepository = inspectionPlanChangeNoteRepository;
        this.archivedInspectionPlanService = archivedInspectionPlanService;
        this.inspectionPlanService = inspectionPlanService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public List<InspectionPlanChangeNoteDTO> save(InspectionPlanChangeNoteDTO changeNoteDTO) {

        InspectionPlanChangeNote changeNote = modelMapper.map(changeNoteDTO, InspectionPlanChangeNote.class);
        ArchivedInspectionPlan archivedPlan = archiveCurrentPlan(changeNoteDTO.getInspectionPlan().getId());
        InspectionPlan updatedPlan = modelMapper.map(inspectionPlanService.update(changeNoteDTO.getInspectionPlan()), InspectionPlan.class);

        changeNote.setInspectionPlan(updatedPlan);
        changeNote.setArchivedInspectionPlan(archivedPlan);

        inspectionPlanChangeNoteRepository.save(changeNote);

        return null;
    }

    private ArchivedInspectionPlan archiveCurrentPlan(long id) {
        InspectionPlan current = modelMapper.map(inspectionPlanService.findById(id), InspectionPlan.class);
        return archivedInspectionPlanService.save(current);
    }
}