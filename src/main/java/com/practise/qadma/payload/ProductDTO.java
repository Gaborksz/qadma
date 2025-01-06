package com.practise.qadma.payload;

import com.practise.qadma.entity.ProductChangeNote;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

    private long id;
    private int partNumber;
    private String name;
    private int revision;
    private Date dateCreated;
    private Date dateModified;
    private long createdBy;
    private long modifiedBy;
    private InspectionPlanDTO inspectionPlan;
    private Set<ProductChangeNote> productChangeNotes;

}
