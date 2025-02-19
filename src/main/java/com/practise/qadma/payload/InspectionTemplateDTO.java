package com.practise.qadma.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class InspectionTemplateDTO {

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
    private Set<InspectionTemplateChangeNoteDTO> changeNotes;
}
