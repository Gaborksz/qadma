package com.practise.qadma.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductChangeNoteDTO extends ChangeNoteDTO {

    private ProductDTO product;
    private ArchivedProductDTO archivedProduct;
    private InspectionPlanChangeNoteDTO inspectionPlanChangeNote;
}
