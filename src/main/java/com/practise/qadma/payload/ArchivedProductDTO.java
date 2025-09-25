package com.practise.qadma.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class ArchivedProductDTO {

    private long id;
    private int partNumber;
    private String name;
    private int revision;
    private Date dateCreated;
    private long createdBy;
    private ArchivedInspectionPlanDTO archivedInspectionPlan;
}
