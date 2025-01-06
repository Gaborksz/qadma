package com.practise.qadma.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ArchivedInspectionPlanDTO {

    private long id;
    private int revision;
    private Date dateCreated;
    private long createdBy;
    Map<Integer, ArchivedInspectionTemplateDTO> templateSequence;
}
