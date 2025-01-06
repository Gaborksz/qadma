package com.practise.qadma.payload.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor

public class InspectionPlanViewDTO {

    private long id;
    private int revision;
    private Date dateCreated;
    private Date dateModified;
    private long createdBy;
    private long modifiedBy;
    private Map<Integer, InspectionTemplateViewDTO> templateSequence;
}
