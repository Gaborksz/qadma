package com.practise.qadma.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor


@Entity
@Table(name = "inspection_template_change_note")
public class InspectionTemplateChangeNote extends ChangeNote {

    @OneToOne
    @JoinColumn(name = "inspection_template_id")
    private InspectionTemplate inspectionTemplate;

    @OneToOne
    @JoinColumn(name = "archived_inspection_template_id")
    private ArchivedInspectionTemplate archivedInspectionTemplate;

    @ManyToMany
    @JoinTable(name = "insp_temp_change_note_product",
            joinColumns = @JoinColumn(name = "insp_temp_change_note_id" ,referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private Set<Product> productsToUpdate;

    @Transient
    private int templateSequenceNumber;
}