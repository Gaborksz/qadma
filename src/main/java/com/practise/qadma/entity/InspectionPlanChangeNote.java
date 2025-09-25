package com.practise.qadma.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "inspection_plan_change_note")
public class InspectionPlanChangeNote extends ChangeNote {


    @ManyToOne
    @JoinColumn(name = "inspection_plan_id")
    private InspectionPlan inspectionPlan;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "archived_inspection_plan_id")
    private ArchivedInspectionPlan archivedInspectionPlan;

    @ManyToMany
    @JoinTable(name = "plan_change_note_id_template_change_note_id",
            joinColumns = @JoinColumn(name = "plan_change_note_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "template_change_note_id", referencedColumnName = "id"))
    private Set<InspectionTemplateChangeNote> templateChangeNotes;


    public void addTemplateChangeNote(InspectionTemplateChangeNote inspectionTemplateChangeNote) {
        if (templateChangeNotes == null) templateChangeNotes = new HashSet<>();
        templateChangeNotes.add(inspectionTemplateChangeNote);

        Set<InspectionPlanChangeNote> inspectionPlanChangeNotes = inspectionTemplateChangeNote.getInspectionPlanChangeNotes();

        if (inspectionPlanChangeNotes == null || !inspectionPlanChangeNotes.contains(this)) {
            inspectionTemplateChangeNote.addInspectionPlanChangeNote(this);
        }
    }
}
