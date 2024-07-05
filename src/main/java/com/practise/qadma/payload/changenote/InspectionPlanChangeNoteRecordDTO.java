package com.practise.qadma.payload.changenote;

import com.practise.qadma.payload.inspectionplan.ArchivedInspectionPlanDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class InspectionPlanChangeNoteRecordDTO extends ChangeNoteDTO{

    private ArchivedInspectionPlanDTO archivedInspectionPlan;

    private Set<InspectionTemplateChangeNoteDTO> inspectionTemplateChangeNotes;
}
