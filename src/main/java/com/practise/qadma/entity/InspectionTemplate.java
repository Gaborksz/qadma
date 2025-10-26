package com.practise.qadma.entity;

import com.practise.qadma.auth.entity.QadmaUser;
import jakarta.persistence.*;
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
@Table(name = "inspection_template")
public class InspectionTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

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



    @Column(name = "attribute_inspection")
    private boolean attributeInspection;

    @Column(name = "specification_description")
    private String specificationDescription;

    @Column(name = "specification_value")
    private int specificationValue;

    @Column(name = "plus_tolerance")
    private int plusTolerance;

    @Column(name = "minus_tolerance")
    private int minusTolerance;

    @OneToMany(mappedBy = "inspectionTemplate")
    Set<InspectionTemplateChangeNote> changeNotes;

    public void setCreatedBy(QadmaUser createdBy) {
        this.createdBy = createdBy;
        this.creatorId = this.createdBy != null ? this.createdBy.getId() : 0;
    }

    public void setModifiedBy(QadmaUser modifiedBy) {
        this.modifiedBy = modifiedBy;
        this.modifierId = this.modifiedBy != null ? this.modifiedBy.getId() : 0;
    }
}
