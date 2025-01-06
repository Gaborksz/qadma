package com.practise.qadma.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ProductChangeNoteDTO {

    private long id;
    private String changeDescription;
    private long createdBy;
    private Date dateCreated;
    private ProductDTO product;
    private ArchivedProductDTO archivedProduct;
    private InspectionPlanChangeNoteDTO inspectionPlanChangeNote;
}
