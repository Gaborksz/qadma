package com.practise.qadma.supportclasses;

import com.practise.qadma.entity.*;
import com.practise.qadma.entity.Product;
import com.practise.qadma.service.ArchivedInspectionTemplateService;
import com.practise.qadma.service.InspectionTemplateChangeNoteService;
import com.practise.qadma.service.InspectionTemplateService;
import com.practise.qadma.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class InspectionTemplateChangeManager {

    private final ArchivedInspectionTemplateService archivedInspectionTemplateService;
    private final InspectionTemplateService inspectionTemplateService;
    private final InspectionTemplateChangeNoteService inspectionTemplateChangeNoteService;
    private final ProductService productService;
    private final InspectionPlanChangeManager inspectionPlanChangeManager;
    private  final ObjectFactoryForInspectionTemplateChangeNote objectFactoryForInspectionTemplateChangeNote;

    private InspectionTemplateChangeNote submittedTemplateChangeNote;
    private InspectionTemplate submittedTemplate;
    private long originalTemplateId;
    private int numberOfProductsToUpdate;
    private Set<Product> productsUsingTemplate;
    private int numberOfProductsUsingTemplate;
    Map<Product, InspectionPlanChangeNote> copyOfProductsWithPlanChangeNotes;


    @Autowired
    public InspectionTemplateChangeManager(ArchivedInspectionTemplateService archivedInspectionTemplateService, InspectionTemplateChangeNoteService inspectionTemplateChangeNoteService, InspectionTemplateService inspectionTemplateService, ProductService productService, InspectionPlanChangeManager inspectionPlanChangeManager, ObjectFactoryForInspectionTemplateChangeNote objectFactoryForInspectionTemplateChangeNote) {
        this.archivedInspectionTemplateService = archivedInspectionTemplateService;
        this.inspectionTemplateChangeNoteService = inspectionTemplateChangeNoteService;
        this.inspectionTemplateService = inspectionTemplateService;
        this.productService = productService;
        this.inspectionPlanChangeManager = inspectionPlanChangeManager;
        this.objectFactoryForInspectionTemplateChangeNote = objectFactoryForInspectionTemplateChangeNote;
    }

    @Transactional
    public Map<Product, InspectionPlanChangeNote> processTemplateChangeNote(
            long updatedInspectionPlanId,
            InspectionTemplateChangeNote templateChangeNote,
            Map<Product, InspectionPlanChangeNote> productsWithChangeNotes) {

        initializeManagerVariables(updatedInspectionPlanId, templateChangeNote, productsWithChangeNotes);

        persistSubmittedTemplate();
        archiveSubmittedTemplate();
        inspectionTemplateChangeNoteService.save(submittedTemplateChangeNote);

        if (numberOfProductsToUpdate > 0) {

            templateChangeNote.getProductsToUpdate().forEach(product -> {

                InspectionPlanChangeNote planChangeNote = copyOfProductsWithPlanChangeNotes.get(product);

                if (planChangeNote == null) {
                    planChangeNote = generatePlanChangeNote(product);
                    copyOfProductsWithPlanChangeNotes.put(product, planChangeNote);
                }

                planChangeNote.addTemplateChangeNote(templateChangeNote);

                replaceCurrentWithUpdatedTemplate(planChangeNote.getInspectionPlan());
            });
        }

        return putPlanChangeNotesOnToOriginalMap(productsWithChangeNotes);
    }


    private InspectionPlanChangeNote generatePlanChangeNote(Product product) {

        return inspectionPlanChangeManager.createPlanChangeNoteForProduct(
                product.getId(),
                submittedTemplateChangeNote.getDateCreated(),
                submittedTemplateChangeNote.getCreatedBy(),
                generateChangeDescription());
    }


    private void persistSubmittedTemplate() {
        if (shouldPersistAsNewTemplate()) {
            submittedTemplate.setId(0);
            inspectionTemplateService.save(submittedTemplate);
        } else {
            inspectionTemplateService.update(submittedTemplate);
        }
    }


    private void archiveSubmittedTemplate() {
        submittedTemplateChangeNote.setArchivedInspectionTemplate(
                archivedInspectionTemplateService.archiveInspectionTemplate(submittedTemplate));
    }


    private boolean shouldPersistAsNewTemplate() {
        return numberOfProductsToUpdate < numberOfProductsUsingTemplate || isNewTemplate();
    }


    private boolean isNewTemplate() {
        return submittedTemplate.getId() == 0;
    }


    private int countProductsToUpdate() {
        return Optional.ofNullable(submittedTemplateChangeNote.getProductsToUpdate()).map(Set::size).orElse(0);
    }


    private String generateChangeDescription() {
        return submittedTemplateChangeNote.getInspectionTemplate().getId() + " " +
                submittedTemplateChangeNote.getInspectionTemplate().getTitle() + " " +
                submittedTemplateChangeNote.getChangeDescription() + ".";
    }


    private void replaceCurrentWithUpdatedTemplate(InspectionPlan managedPlan) {
        int sequenceNumber = managedPlan.getTemplateSequence().entrySet().stream().filter(s -> s.getValue().getId() == originalTemplateId).map(Map.Entry::getKey).findFirst().orElse(-1);

        if (sequenceNumber > -1) {
            managedPlan.getTemplateSequence().put(sequenceNumber, submittedTemplate);
        }
    }


    private void initializeManagerVariables(
            long updatedInspectionPlanId,
            InspectionTemplateChangeNote templateChangeNote,
            Map<Product, InspectionPlanChangeNote> productsWithChangeNotes) {

        objectFactoryForInspectionTemplateChangeNote.assignManagedProductsToTemplateChangeNote(templateChangeNote);

        this.submittedTemplateChangeNote = templateChangeNote;
        this.submittedTemplate = templateChangeNote.getInspectionTemplate();
        this.originalTemplateId = submittedTemplate.getId();
        this.copyOfProductsWithPlanChangeNotes = productsWithChangeNotes != null ? new HashMap<>(productsWithChangeNotes) : new HashMap<>();
        numberOfProductsToUpdate = countProductsToUpdate();
        this.productsUsingTemplate = originalTemplateId != 0 ? productService.getProductsByInspectionTemplateId(originalTemplateId) : new HashSet<>();
        this.numberOfProductsUsingTemplate = wasSubmittedTemplateAlreadyPartOfUpdatedPlan(updatedInspectionPlanId) ?
                productsUsingTemplate.size() - 1 :
                productsUsingTemplate.size();
    }

    private boolean wasSubmittedTemplateAlreadyPartOfUpdatedPlan(long updatedInspectionPlanId) {

        for (Product product : productsUsingTemplate) {

            InspectionPlan inspectionPlan = product.getInspectionPlan();

            if (inspectionPlan.getId() == updatedInspectionPlanId) {
                for (Map.Entry<Integer, InspectionTemplate> sequence : inspectionPlan.getTemplateSequence().entrySet()) {
                    if (sequence.getValue().getId() == originalTemplateId) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Map<Product, InspectionPlanChangeNote> putPlanChangeNotesOnToOriginalMap(Map<Product, InspectionPlanChangeNote> productsWithChangeNotes) {

        copyOfProductsWithPlanChangeNotes.forEach(productsWithChangeNotes::put);

        copyOfProductsWithPlanChangeNotes.clear();

        return productsWithChangeNotes;
    }
}
