package com.practise.qadma.payload;

import com.practise.qadma.auth.payload.QadmaUserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

    private long id;
    private int partNumber;
    private String productName;
    private int revision;
    private Date dateCreated;
    private Date dateModified;
    private QadmaUserDTO createdBy;
    private QadmaUserDTO modifiedBy;
    private InspectionPlanDTO inspectionPlan;
    private Set<ProductChangeNoteDTO> productChangeNotes;
}
