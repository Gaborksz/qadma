package com.practise.qadma.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "inspection_plan_change_note")
public class InspectionPlanChangeNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description")
    private String changeDescription;

    @Column(name = "createdBy")
    private long createdBy;

    @Column(name = "date_created")
    private Date dateCreated;

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

    public void addTextToChangeDescription(String changeDescription) {

        if (this.changeDescription == null) this.changeDescription = "";

        this.changeDescription = this.changeDescription + "\n" + changeDescription;
    }
}
