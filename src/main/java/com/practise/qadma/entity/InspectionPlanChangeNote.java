package com.practise.qadma.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "change_note")
public class InspectionPlanChangeNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "createdBy")
    private long createdBy;

    @Column(name = "date_created")
    private Date dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inspection_plan_id")
    private InspectionPlan inspectionPlan;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "archived_inspection_plan_id")
    private ArchivedInspectionPlan archivedInspectionPlan;
}
