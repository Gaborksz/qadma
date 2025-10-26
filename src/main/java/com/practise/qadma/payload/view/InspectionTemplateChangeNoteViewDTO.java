package com.practise.qadma.payload.view;

import com.practise.qadma.auth.payload.QadmaUserDTO;
import com.practise.qadma.payload.InspectionTemplateDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

public class InspectionTemplateChangeNoteViewDTO {

    private long id;
    private QadmaUserDTO createdBy;
    private Date dateCreated;
    private String changeDescription;
    private InspectionTemplateDTO inspectionTemplate;
    private Set<ProductViewDTO> productsToUpdate;
}
