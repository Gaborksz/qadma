package com.practise.qadma.payload.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

public class InspectionTemplateViewDTO {

    private long id;
    private String title;
    private int revision;
    private Date dateCreated;
    private Date dateModified;
    private long createdBy;
    private long modifiedBy;
    private boolean attributeInspection;
    private String specificationDescription;
    private int specificationValue;
    private int plusTolerance;
    private int minusTolerance;
}
