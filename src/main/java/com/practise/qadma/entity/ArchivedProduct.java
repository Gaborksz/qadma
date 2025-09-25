package com.practise.qadma.entity;

import com.practise.qadma.auth.entity.QadmaUser;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "archived_product")
public class ArchivedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "part_number")
    private int partNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "revision")
    private int revision;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "created_by_userid")
    private long creatorId;

    @Transient
    private QadmaUser createdBy;

    @OneToOne()
    @JoinColumn(name = "archived_inspection_plan_id")
    private ArchivedInspectionPlan archivedInspectionPlan;

    public void setCreatedBy(QadmaUser createdBy) {
        this.createdBy = createdBy;
        this.creatorId = this.createdBy != null ? this.createdBy.getId() : 0;
    }
}
