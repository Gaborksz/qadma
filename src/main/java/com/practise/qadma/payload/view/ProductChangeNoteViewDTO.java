package com.practise.qadma.payload.view;

import com.practise.qadma.entity.Product;
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
    private Product product;
    private long archivedProductId;
    private InspectionPlanChangeNoteViewDTO inspectionPlanChangeNote;
}
