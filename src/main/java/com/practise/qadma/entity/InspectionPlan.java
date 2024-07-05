package com.practise.qadma.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "inspection_plan")
public class InspectionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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

    @Column(name = "status")
    private String status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "inspection_plan_id_inspection_template_id",
            joinColumns = @JoinColumn(name = "inspection_plan_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "inspection_template_id", referencedColumnName = "id"))
    @MapKeyColumn(name = "sequence_number")
    Map<Integer, InspectionTemplate> templateSequence;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "inspection_plan_id")
    Set<InspectionPlanChangeNote> inspectionPlanChangeNotes;
}
