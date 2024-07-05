package com.practise.qadma.payload.changenote;

import com.practise.qadma.payload.ProductDTO;
import com.practise.qadma.payload.inspectiontemplate.InspectionTemplateDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class InspectionTemplateChangeNoteDTO extends ChangeNoteDTO {

    private InspectionTemplateDTO inspectionTemplate;

    private Set<ProductDTO> products;
}
