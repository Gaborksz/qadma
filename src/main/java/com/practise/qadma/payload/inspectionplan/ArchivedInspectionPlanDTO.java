package com.practise.qadma.payload.inspectionplan;

import com.practise.qadma.payload.inspectiontemplate.ArchivedInspectionTemplateDTO;
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

    private String status;

    Map<Integer, ArchivedInspectionTemplateDTO> templateSequence;
}
