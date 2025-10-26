package com.practise.qadma.service;

import com.practise.qadma.auth.entity.QadmaUser;
import com.practise.qadma.auth.service.QadmaUserService;
import com.practise.qadma.dao.ArchivedInspectionTemplateRepository;
import com.practise.qadma.dao.InspectionTemplateChangeNoteRepository;
import com.practise.qadma.entity.ArchivedInspectionTemplate;
import com.practise.qadma.entity.ChangeNote;
import com.practise.qadma.entity.InspectionTemplate;
import com.practise.qadma.entity.InspectionTemplateChangeNote;
import com.practise.qadma.exception.InspectionTemplateNotSavedException;
import com.practise.qadma.payload.InspectionTemplateChangeNoteDTO;
import com.practise.qadma.payload.InspectionTemplateDTO;
import com.practise.qadma.payload.view.InspectionTemplateChangeNoteViewDTO;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InspectionTemplateChangeNoteServiceImpl implements InspectionTemplateChangeNoteService {

    private final InspectionTemplateChangeNoteRepository inspectionTemplateChangeNoteRepository;
    private final InspectionTemplateService inspectionTemplateService;
    private final ArchivedInspectionTemplateService archivedInspectionTemplateService;
    private final QadmaUserService qadmaUserService;
    private final ModelMapper modelMapper;


    @Autowired
    public InspectionTemplateChangeNoteServiceImpl(InspectionTemplateChangeNoteRepository inspectionTemplateChangeNoteRepository, ArchivedInspectionTemplateRepository archivedInspectionTemplateRepository, InspectionTemplateService inspectionTemplateService, ArchivedInspectionTemplateService archivedInspectionTemplateService, QadmaUserService qadmaUserService, ModelMapper modelMapper) {
        this.inspectionTemplateChangeNoteRepository = inspectionTemplateChangeNoteRepository;
        this.inspectionTemplateService = inspectionTemplateService;
        this.archivedInspectionTemplateService = archivedInspectionTemplateService;
        this.qadmaUserService = qadmaUserService;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public InspectionTemplateChangeNoteViewDTO save(InspectionTemplateChangeNoteDTO changeNoteDTO) {

        InspectionTemplate managedTemplate = null;
        InspectionTemplateChangeNote changeNote = modelMapper.map(changeNoteDTO, InspectionTemplateChangeNote.class);

        if (changeNoteDTO.getInspectionTemplate() != null) {
            InspectionTemplateDTO managedTemplateDTO = inspectionTemplateService.save(changeNoteDTO.getInspectionTemplate());
            managedTemplate = modelMapper.map(managedTemplateDTO, InspectionTemplate.class);
            changeNote.setInspectionTemplate(managedTemplate);
        }

        if (managedTemplate == null) {
            throw new InspectionTemplateNotSavedException("Inspection template has not been saved " );
        }

        ArchivedInspectionTemplate archivedTemplate = archivedInspectionTemplateService.archiveInspectionTemplate(managedTemplate);
        changeNote.setArchivedInspectionTemplate(archivedTemplate);

        InspectionTemplateChangeNote managedChangeNote = inspectionTemplateChangeNoteRepository.save(changeNote);

        Set<InspectionTemplateChangeNote> changeNotes = new HashSet<>();
        changeNotes.add(managedChangeNote);
        populateChangeNoteWithUsers(changeNotes);

        return modelMapper.map(managedChangeNote, InspectionTemplateChangeNoteViewDTO.class);
    }

    private void populateChangeNoteWithUsers(Set<InspectionTemplateChangeNote> changeNotes){

    Set<Long> creatorIds =   changeNotes.stream().map(ChangeNote::getCreatorId).collect(Collectors.toSet());

        Set<QadmaUser> users = qadmaUserService.findUsersByIds(creatorIds);

        Map<Long, QadmaUser> userMap = users.stream().collect(Collectors.toMap(QadmaUser::getId, qadmaUser -> qadmaUser));

        changeNotes.forEach( cn -> cn.setCreatedBy(userMap.get(cn.getCreatorId())));
    }
}
