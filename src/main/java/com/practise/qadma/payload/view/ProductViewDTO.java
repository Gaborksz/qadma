package com.practise.qadma.payload.view;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

public class ProductViewDTO {

    private long id;
    private int partNumber;
    private String name;
    private int revision;
    private Date dateCreated;
    private Date dateModified;
    private long createdBy;
    private long modifiedBy;
    private long inspectionPlanId;
}
