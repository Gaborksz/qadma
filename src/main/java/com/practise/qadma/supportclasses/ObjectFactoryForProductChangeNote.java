package com.practise.qadma.supportclasses;

import com.practise.qadma.entity.InspectionPlan;
import com.practise.qadma.entity.ProductChangeNote;
import org.springframework.stereotype.Component;

@Component
public class ObjectFactoryForProductChangeNote {


    public void addInspectionPlanToChangeNote(ProductChangeNote productChangeNote) {
        InspectionPlan inspectionPlan = productChangeNote.getProduct().getInspectionPlan();
        productChangeNote.getInspectionPlanChangeNote().setInspectionPlan(inspectionPlan);
    }
}
