package com.practise.qadma.entity;

import com.practise.qadma.auth.entity.QadmaUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ChangeNote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "description")
    private String changeDescription;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "created_by_userid")
    private long creatorId;

    @Transient
    private QadmaUser createdBy;

    public void setCreatedBy(QadmaUser createdBy) {
        this.createdBy = createdBy;
        this.creatorId = this.createdBy != null ? this.createdBy.getId() : 0;
    }
}
