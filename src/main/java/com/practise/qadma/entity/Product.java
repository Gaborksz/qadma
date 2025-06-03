package com.practise.qadma.entity;

import com.practise.qadma.auth.entity.QadmaUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
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

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "createdby_userid")
    private long creatorId;

    @Transient
    private QadmaUser createdBy;


    @Column(name = "date_modified")
    private Date dateModified;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "modifiedBy_userid")
    long modifierId;
    
    @Transient
    private QadmaUser modifiedBy;

    
    @OneToOne
    @JoinColumn(name = "inspection_plan_id")
    private InspectionPlan inspectionPlan;

    @OneToMany(mappedBy = "product")
    private Set<ProductChangeNote> productChangeNotes;

    public void setCreatedBy(QadmaUser createdBy) {
        this.creatorId = createdBy != null ? createdBy.getId() : 0;
        this.createdBy = createdBy;
    }
}