package com.practise.qadma.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ArchivedInspectionTemplateDTO {

    private long id;
    private String title;
    private int revision;
    private Date dateCreated;
    private long createdBy;
    private boolean attributeInspection;
    private String specificationDescription;
    private int specificationValue;
    private int plusTolerance;
    private int minusTolerance;
}
