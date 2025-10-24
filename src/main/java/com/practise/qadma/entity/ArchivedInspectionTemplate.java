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
@Table(name = "archived_inspection_template")
public class ArchivedInspectionTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "revision")
    private int revision;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "created_by_userid")
    private long creatorId;

    @Column(name = "attribute_inspection")
    private boolean attributeInspection;

    @Column(name = "specification_description")
    private String specificationDescription;

    @Column(name = "specification_value")
    private int specificationValue;

    @Column(name = "plus_tolerance")
    private int plusTolerance;

    @Column(name = "minus_tolerance")
    private int minusTolerance;
}
