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
@Table(name = "inspection_template_change_note")
public class InspectionTemplateChangeNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_by")
    private long createdBy;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "description")
    private String changeDescription;

    @ManyToMany(mappedBy = "templateChangeNotes")
    Set<InspectionPlanChangeNote> inspectionPlanChangeNotes;

    @OneToOne
    @JoinColumn(name = "inspection_template_id")
    private InspectionTemplate inspectionTemplate;

    @OneToOne
    @JoinColumn(name = "archived_inspection_template_id")
    private ArchivedInspectionTemplate archivedInspectionTemplate;

    @Transient
    private Set<Product> productsToUpdate;

    @Transient
    private int templateSequenceNumber;


    public void addInspectionPlanChangeNote(InspectionPlanChangeNote inspectionPlanChangeNote) {

        if (inspectionPlanChangeNotes == null) inspectionPlanChangeNotes = new HashSet<>();
        inspectionPlanChangeNotes.add(inspectionPlanChangeNote);

        Set<InspectionTemplateChangeNote> inspectionTemplateChangeNotes = inspectionPlanChangeNote.getTemplateChangeNotes();

        if (inspectionTemplateChangeNotes == null || !inspectionTemplateChangeNotes.contains(this)) {
            inspectionPlanChangeNote.addTemplateChangeNote(this);
        }
    }
}