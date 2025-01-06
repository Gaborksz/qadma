package com.practise.qadma.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "product_change_note")
public class ProductChangeNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description")
    private String changeDescription;

    @Column(name = "created_by")
    private long createdBy;

    @Column(name = "date_created")
    private Date dateCreated;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "archived_product_id")
    private ArchivedProduct archivedProduct;

    @OneToOne
    @JoinColumn(name = "plan_change_note_id")
    private InspectionPlanChangeNote inspectionPlanChangeNote;

    public void addTextToChangeDescription(String changeDescription) {

        if (this.changeDescription == null) this.changeDescription = "";

        this.changeDescription = this.changeDescription + "\n" + changeDescription;
    }
}
