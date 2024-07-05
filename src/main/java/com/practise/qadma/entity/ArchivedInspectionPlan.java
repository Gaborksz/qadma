package com.practise.qadma.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "archived_inspection_plan")
public class ArchivedInspectionPlan {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "revision")
    private int revision;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "created_by")
    private long createdBy;

    @Column(name = "status")
    private String status;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "archived_insp_plan_id_archived_insp_template_id",
            joinColumns = @JoinColumn(name = "archived_inspection_plan_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "archived_inspection_template_id", referencedColumnName = "id"))
    @MapKeyColumn(name = "sequence_number")
    Map<Integer, ArchivedInspectionTemplate> templateSequence;
}
