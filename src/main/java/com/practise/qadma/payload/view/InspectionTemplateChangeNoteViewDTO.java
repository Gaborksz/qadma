package com.practise.qadma.payload.view;

import com.practise.qadma.payload.ArchivedProductDTO;
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
    private long createdBy;
    private Date dateCreated;
    private String changeDescription;
    private long archivedInspectionTemplateId;
    private String archivedInspectionTemplateTitle;
    private Set<ArchivedProductDTO> productsToUpdate;
}
