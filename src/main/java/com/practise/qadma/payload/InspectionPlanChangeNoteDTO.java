package com.practise.qadma.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

public class InspectionPlanChangeNoteDTO extends ChangeNoteDTO{

    private InspectionPlanDTO inspectionPlan;
    private Set<InspectionTemplateChangeNoteDTO> templateChangeNotes;
}
