package com.practise.qadma.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class InspectionPlanDTO {

    private long id;
    private int revision;
    private Date dateCreated;
    private Date dateModified;
    private long createdBy;
    private long modifiedBy;
    Map<Integer, InspectionTemplateDTO> templateSequence;
    Set<InspectionPlanChangeNoteDTO> changeNotes;
}
