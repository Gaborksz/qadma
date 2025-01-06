package com.practise.qadma.payload.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

public class InspectionPlanChangeNoteViewDTO {

    private long id;
    private String changeDescription;
    private long createdBy;
    private Date dateCreated;
    private long inspectionPlanId;
    private long archivedInspectionPlanId;
    private int archivedInspectionPlanRevision;
    private Set<InspectionTemplateChangeNoteViewDTO> templateChangeNotes;
}
