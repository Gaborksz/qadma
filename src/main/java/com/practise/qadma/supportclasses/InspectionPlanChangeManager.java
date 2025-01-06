package com.practise.qadma.supportclasses;

import com.practise.qadma.entity.*;
import com.practise.qadma.service.ArchivedInspectionPlanService;
import com.practise.qadma.service.InspectionPlanChangeNoteService;
import com.practise.qadma.service.InspectionPlanService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class InspectionPlanChangeManager {

    private final ArchivedInspectionPlanService archivedInspectionPlanService;
    private final InspectionPlanService inspectionPlanService;
    private final InspectionPlanChangeNoteService inspectionPlanChangeNoteService;
    private final InspectionTemplateChangeManager inspectionTemplateChangeManager;
    private final ObjectFactoryForInspectionPlanChangeNote objectFactoryForInspectionPlanChangeNote;
    private InspectionPlanChangeNote submittedPlanChangeNote;
    private Set<InspectionTemplateChangeNote> submittedTemplateChangeNotes;
    private Map<Product, InspectionPlanChangeNote> copyOfProductsWithPlanChangeNotes;
    private Product submittedProduct;


    @Autowired
    public InspectionPlanChangeManager(ArchivedInspectionPlanService archivedInspectionPlanService, InspectionPlanChangeNoteService inspectionPlanChangeNoteService, InspectionPlanService inspectionPlanService, @Lazy InspectionTemplateChangeManager inspectionTemplateChangeManager, ObjectFactoryForInspectionPlanChangeNote objectFactoryForInspectionPlanChangeNote) {
        this.archivedInspectionPlanService = archivedInspectionPlanService;
        this.inspectionPlanChangeNoteService = inspectionPlanChangeNoteService;
        this.inspectionPlanService = inspectionPlanService;
        this.inspectionTemplateChangeManager = inspectionTemplateChangeManager;
        this.objectFactoryForInspectionPlanChangeNote = objectFactoryForInspectionPlanChangeNote;
    }


    @Transactional
    public Map<Product, InspectionPlanChangeNote> processPlanChangeNote(
            Product product,
            InspectionPlanChangeNote inspectionPlanChangeNote,
            Map<Product, InspectionPlanChangeNote> productsWithChangeNotes) {

        initializeManagerVariables(product, inspectionPlanChangeNote, productsWithChangeNotes);
        objectFactoryForInspectionPlanChangeNote.setChangeNoteRelationships(inspectionPlanChangeNote);

        if (submittedTemplateChangeNotes != null && !submittedTemplateChangeNotes.isEmpty()) {
            this.copyOfProductsWithPlanChangeNotes = generateProductsWithPlanChangeNotesMap();
        }
        submittedPlanChangeNote.addTextToChangeDescription(generateChangeDescriptionFromTemplateChangeNotes());

        this.copyOfProductsWithPlanChangeNotes.put(submittedProduct, submittedPlanChangeNote);

        persistPlanChangeNotes();

        return putPlanChangeNotesOnToOriginalMap(productsWithChangeNotes);
    }


    private Map<Product, InspectionPlanChangeNote> generateProductsWithPlanChangeNotesMap() {

        submittedTemplateChangeNotes.forEach(templateChangeNote ->
                inspectionTemplateChangeManager.processTemplateChangeNote(
                        submittedPlanChangeNote.getInspectionPlan().getId(),
                        templateChangeNote,
                        copyOfProductsWithPlanChangeNotes));

        return copyOfProductsWithPlanChangeNotes;
    }


    private void persistPlanChangeNotes() {

        copyOfProductsWithPlanChangeNotes.forEach((product, planChangeNote) -> {

            InspectionPlan inspectionPlan = planChangeNote.getInspectionPlan();
            inspectionPlan.setRevision(inspectionPlan.getRevision() + 1);

            persistInspectionPlan(inspectionPlan);

            ArchivedInspectionPlan archivedPlan = archivedInspectionPlanService.archiveInspectionPlan(inspectionPlan);

            planChangeNote.setArchivedInspectionPlan(archivedPlan);

            inspectionPlanChangeNoteService.save(planChangeNote);
        });
    }


    private void persistInspectionPlan(InspectionPlan inspectionPlan) {

        if (inspectionPlan.getId() == 0) {
            inspectionPlanService.save(inspectionPlan);
        } else {
            inspectionPlanService.update(inspectionPlan);
        }
    }


    public InspectionPlanChangeNote createPlanChangeNoteForProduct(
            long productId, Date dateCreated, long createdBy, String changeDescription) {

        InspectionPlan inspectionPlan = inspectionPlanService.findByProductId(productId);
        inspectionPlan.setRevision(inspectionPlan.getRevision() + 1);
        inspectionPlan.setDateModified(dateCreated);

        InspectionPlanChangeNote planChangeNote = new InspectionPlanChangeNote();
        planChangeNote.setInspectionPlan(inspectionPlan);
        planChangeNote.setDateCreated(dateCreated);
        planChangeNote.setCreatedBy(createdBy);
        planChangeNote.setChangeDescription(changeDescription);

        return planChangeNote;
    }


    private void initializeManagerVariables(
            Product product,
            InspectionPlanChangeNote inspectionPlanChangeNote,
            Map<Product, InspectionPlanChangeNote> productsWithChangeNotes) {

        this.submittedPlanChangeNote = inspectionPlanChangeNote;

        if (inspectionPlanChangeNote.getTemplateChangeNotes() != null)
            this.submittedTemplateChangeNotes = submittedPlanChangeNote.getTemplateChangeNotes();

        this.copyOfProductsWithPlanChangeNotes = productsWithChangeNotes != null ?
                productsWithChangeNotes.entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)) :
                new HashMap<>();

        this.submittedProduct = product;
    }

    private Map<Product, InspectionPlanChangeNote> putPlanChangeNotesOnToOriginalMap(Map<Product, InspectionPlanChangeNote> productsWithChangeNotes) {

        copyOfProductsWithPlanChangeNotes.forEach(productsWithChangeNotes::put);

        copyOfProductsWithPlanChangeNotes.clear();

        return productsWithChangeNotes;
    }

    private String generateChangeDescriptionFromTemplateChangeNotes() {

        return submittedTemplateChangeNotes.stream().map(cn ->
                cn.getInspectionTemplate().getId() + " " +
                        cn.getInspectionTemplate().getTitle() + " " +
                        cn.getChangeDescription()).collect(Collectors.joining("\n"));
    }
}
