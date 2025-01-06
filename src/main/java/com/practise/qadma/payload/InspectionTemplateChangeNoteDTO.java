package com.practise.qadma.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class InspectionTemplateChangeNoteDTO extends ChangeNoteDTO {

    private int templateSequenceNumber;
    private InspectionTemplateDTO inspectionTemplate;
    private Set<ProductDTO> productsToUpdate;
}
