package com.practise.qadma.entity;

import com.practise.qadma.payload.ProductDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "InspectionTemplateChangeNote")
public class InspectionTemplateChangeNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "created_by")
    private long createdBy;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "change_description")
    private String changeDescription;

    @OneToOne
    @JoinColumn(name = "inspection_template_id")
    private InspectionTemplate inspectionTemplate;

    @OneToOne
    @JoinColumn(name = "archived_inspection_template_id")
    private ArchivedInspectionTemplate archivedInspectionTemplate;
}