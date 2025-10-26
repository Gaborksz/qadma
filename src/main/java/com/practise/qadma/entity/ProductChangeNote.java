package com.practise.qadma.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString

@Entity
@Table(name = "product_change_note")
public class ProductChangeNote extends ChangeNote {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "archived_product_id")
    private ArchivedProduct archivedProduct;

    @OneToOne
    @JoinColumn(name = "plan_change_note_id")
    private InspectionPlanChangeNote inspectionPlanChangeNote;
}
