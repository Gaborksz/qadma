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
@Table(name = "archived_product")
public class ArchivedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "part_number")
    private int partNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "revision")
    private int revision;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "created_by")
    private long createdBy;

    @OneToOne()
    @JoinColumn(name = "archived_inspection_plan_id")
    private ArchivedInspectionPlan archivedInspectionPlan;
}
