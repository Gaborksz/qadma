package com.practise.qadma.payload.inspectionplan;

import com.practise.qadma.payload.inspectiontemplate.InspectionTemplateDTO;
import com.practise.qadma.payload.changenote.InspectionPlanChangeNoteRecordDTO;
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

    private String status;

    Map<Integer, InspectionTemplateDTO> templateSequence;

    Set<InspectionPlanChangeNoteRecordDTO> changeNotes;
}
