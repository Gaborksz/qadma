package com.practise.qadma.service;

import com.practise.qadma.dao.ArchivedInspectionTemplateRepository;
import com.practise.qadma.dao.InspectionTemplateChangeNoteRepository;
import com.practise.qadma.entity.InspectionTemplate;
import com.practise.qadma.entity.InspectionTemplateChangeNote;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InspectionTemplateChangeNoteServiceImpl implements InspectionTemplateChangeNoteService {

    private InspectionTemplateChangeNoteRepository inspectionTemplateChangeNoteRepository;
    private ArchivedInspectionTemplateService archivedInspectionTemplateService;

    @Autowired
    public InspectionTemplateChangeNoteServiceImpl(InspectionTemplateChangeNoteRepository inspectionTemplateChangeNoteRepository, ArchivedInspectionTemplateRepository archivedInspectionTemplateRepository, ArchivedInspectionTemplateService archivedInspectionTemplateService) {
        this.inspectionTemplateChangeNoteRepository = inspectionTemplateChangeNoteRepository;
        this.archivedInspectionTemplateService = archivedInspectionTemplateService;
    }


    @Override
    public InspectionTemplateChangeNote createChangeNoteForNewTemplate(InspectionTemplate inspectionTemplate) {

        InspectionTemplateChangeNote changeNote = new InspectionTemplateChangeNote();

        return changeNote;
    }

    @Transactional
    @Override
    public InspectionTemplateChangeNote save(InspectionTemplateChangeNote changeNote) {

        return inspectionTemplateChangeNoteRepository.save(changeNote);
    }

    @Override
    public InspectionTemplateChangeNote saveAndFlush(InspectionTemplateChangeNote changeNote) {

        return inspectionTemplateChangeNoteRepository.saveAndFlush(changeNote);
    }
}
