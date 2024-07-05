package com.practise.qadma.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "product")
public class Product {

    @Id     @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "part_number")
    private int partNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "revision")
    private int revision;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumns(@JoinColumn(name = "inspection_plan_id"))
    private InspectionPlan inspectionPlan;
}