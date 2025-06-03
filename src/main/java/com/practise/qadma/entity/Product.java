package com.practise.qadma.entity.product;

import com.practise.qadma.auth.entity.QadmaUser;
import com.practise.qadma.entity.InspectionPlan;
import com.practise.qadma.entity.ProductChangeNote;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "part_number")
    private int partNumber;

    @NotBlank
    @Column(name = "name")
    private String productName;

    @Column(name = "revision")
    private int revision;

    
    @Column(name = "date_created")
    private Date dateCreated;
        
    @Column(name = "createdby_userid")
    long createdById;

    @Transient
    private QadmaUser createdBy;
    
    
    @Column(name = "date_modified")
    private Date dateModified;

    @Column(name = "modifiedBy_userid")
    long modifiedById;
    
    @Transient
    private QadmaUser modifiedBy;

    
    @OneToOne
    @JoinColumn(name = "inspection_plan_id")
    private InspectionPlan inspectionPlan;

    @OneToMany(mappedBy = "product")
    private Set<ProductChangeNote> productChangeNotes;
}