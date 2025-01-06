package com.practise.qadma.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "product")
public class Product {

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

    @Column(name = "date_modified")
    private Date dateModified;

    @Column(name = "created_by")
    private long createdBy;

    @Column(name = "modified_by")
    private long modifiedBy;

    @OneToOne
    @JoinColumn(name = "inspection_plan_id")
    private InspectionPlan inspectionPlan;

    @OneToMany(mappedBy = "product")
    private Set<ProductChangeNote> productChangeNotes;
}