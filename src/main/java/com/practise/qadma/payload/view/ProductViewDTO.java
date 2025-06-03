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
    private String productName;
    private int revision;
    private Date dateCreated;
    private Date dateModified;
    private long creatorId;
    private String creatorName;
    private long modifierId;
    private String modifierName;
    private long inspectionPlanId;
}
