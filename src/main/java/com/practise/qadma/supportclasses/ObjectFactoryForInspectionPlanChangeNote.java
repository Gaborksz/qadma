package com.practise.qadma.supportclasses;

import com.practise.qadma.entity.InspectionPlanChangeNote;
import com.practise.qadma.entity.InspectionTemplate;
import org.springframework.stereotype.Component;

@Component
public class ObjectFactoryForInspectionPlanChangeNote {

    public InspectionPlanChangeNote setChangeNoteRelationships(
            InspectionPlanChangeNote inspectionPlanChangeNote
    ) {
        assignTemplatesToTemplateChangeNotes(inspectionPlanChangeNote);
        addPlanChangeNoteToTemplateChangeNotes(inspectionPlanChangeNote);

        return inspectionPlanChangeNote;
    }

    private void assignTemplatesToTemplateChangeNotes(InspectionPlanChangeNote inspectionPlanChangeNote) {
        inspectionPlanChangeNote.getTemplateChangeNotes().forEach(changeNote -> {
            InspectionTemplate inspectionTemplate = inspectionPlanChangeNote.getInspectionPlan().getTemplateSequence()
                    .get(changeNote.getTemplateSequenceNumber());

            changeNote.setInspectionTemplate(inspectionTemplate);
        });
    }

    private void addPlanChangeNoteToTemplateChangeNotes(InspectionPlanChangeNote inspectionPlanChangeNote) {
        inspectionPlanChangeNote.getTemplateChangeNotes().forEach(changeNote ->
                changeNote.addInspectionPlanChangeNote(inspectionPlanChangeNote));
    }
}
