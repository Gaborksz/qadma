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
    @Column(name = "created_by_userid")
    private long creatorId;

    @Transient
    private QadmaUser createdBy;

    @Column(name = "date_modified")
    private Date dateModified;

    @Setter(AccessLevel.PROTECTED)
    @Column(name = "modified_by_userid")
    long modifierId;
    
    @Transient
    private QadmaUser modifiedBy;
    
    @OneToOne
    @JoinColumn(name = "inspection_plan_id")
    private InspectionPlan inspectionPlan;

    @OneToMany(mappedBy = "product")
    private Set<ProductChangeNote> productChangeNotes;

    public void setCreatedBy(QadmaUser createdBy) {
        this.createdBy = createdBy;
        this.creatorId = this.createdBy != null ? this.createdBy.getId() : 0;
    }

    public void setModifiedBy(QadmaUser modifiedBy) {
        this.modifiedBy = modifiedBy;
        this.modifierId = this.modifiedBy != null ? this.modifiedBy.getId() : 0;
    }
}