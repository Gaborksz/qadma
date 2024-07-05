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
@Table(name = "inspection_evaluation")
public class InspectionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "pass")
    private boolean pass;

    @Column(name = "fail")
    private boolean fail;

    @Column(name = "value")
    private double value;

    @Column(name = "inspection_template_id")
    private long inspectionTemplateId;

    @Column(name = "date")
    private Date date;

    @Column(name = "userId")
    private int userId;
}
