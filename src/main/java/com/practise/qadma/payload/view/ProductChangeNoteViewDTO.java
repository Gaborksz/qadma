package com.practise.qadma.payload.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ProductChangeNoteViewDTO {

    private long id;
    private String changeDescription;
    private long createdBy;
    private Date dateCreated;
    private ProductViewDTO productDTO;
    private long archivedProductId;
    private InspectionPlanChangeNoteViewDTO inspectionPlanChangeNote;
}
