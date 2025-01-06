package com.practise.qadma.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor


@Entity
public class InspectionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "revision")
    private int revision;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "date_modified")
    private Date dateModified;

    @Column(name = "created_by")
    private long createdBy;

    @Column(name = "modified_by")
    private long modifiedBy;

    @ManyToMany
    @JoinTable(name = "inspection_plan_id_inspection_template_id",
            joinColumns = @JoinColumn(name = "inspection_plan_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "inspection_template_id", referencedColumnName = "id"))
    @MapKeyColumn(name = "sequence_number")
    Map<Integer, InspectionTemplate> templateSequence;

    @OneToMany(mappedBy = "inspectionPlan")
    Set<InspectionPlanChangeNote> changeNotes;

    public void addSequence(int sequenceNumber, InspectionTemplate inspectionTemplate) {
        if (templateSequence == null) templateSequence = new HashMap<>();
        templateSequence.put(sequenceNumber, inspectionTemplate);
    }
}