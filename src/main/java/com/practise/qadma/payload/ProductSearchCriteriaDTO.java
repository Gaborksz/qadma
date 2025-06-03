package com.practise.qadma.payload;

import com.practise.qadma.enums.DateFilterSelector;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class ProductSearchCriteriaDTO {

    private long id;
    private int partNumber;
    private String productName;
    private int revision;
    private DateFilterSelector dateCreatedSelector;
    private Date dateCreatedFrom;
    private Date dateCreatedTo;
    private DateFilterSelector dateModifiedSelector;
    private Date dateModifiedFrom;
    private Date dateModifiedTo;
    private long createdBy;
    private long modifiedBy;
    private InspectionPlanDTO inspectionPlanId;
}
