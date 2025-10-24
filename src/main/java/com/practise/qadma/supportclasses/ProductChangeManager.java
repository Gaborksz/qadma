package com.practise.qadma.supportclasses;

import com.practise.qadma.entity.ArchivedProduct;
import com.practise.qadma.entity.InspectionPlanChangeNote;
import com.practise.qadma.entity.Product;
import com.practise.qadma.entity.ProductChangeNote;
import com.practise.qadma.service.ArchivedProductService;
import com.practise.qadma.service.ProductChangeNoteService;
import com.practise.qadma.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductChangeManager {

//    private final ProductService productService;
//    private final ArchivedProductService archivedProductService;
//    private final InspectionPlanChangeManager inspectionPlanChangeManager;
//    private final ObjectFactoryForProductChangeNote objectFactoryForInspectionPlanChangeNote;
//    private final ProductChangeNoteService productChangeNoteService;
//    private Product submittedProduct;
//    private ProductChangeNote submittedProductChangeNote;
//    private InspectionPlanChangeNote submittedInspectionPlanChangeNote;
//    private Set<ProductChangeNote> productChangeNotes;
//
//
//    @Autowired
//    public ProductChangeManager(InspectionPlanChangeManager inspectionPlanChangeManager, ProductService productService, ArchivedProductService archivedProductService, ObjectFactoryForProductChangeNote objectFactoryForInspectionPlanChangeNote, ProductChangeNoteService productChangeNoteService) {
//        this.inspectionPlanChangeManager = inspectionPlanChangeManager;
//        this.productService = productService;
//        this.archivedProductService = archivedProductService;
//        this.objectFactoryForInspectionPlanChangeNote = objectFactoryForInspectionPlanChangeNote;
//        this.productChangeNoteService = productChangeNoteService;
//    }
//
//
//    @Transactional
//    public List<ProductChangeNote> processProductChangeNote(ProductChangeNote productChangeNote) {
//
//        initializeManagerVariables(productChangeNote);
//
//        productChangeNotes.add(submittedProductChangeNote);
//
////        if (submittedInspectionPlanChangeNote != null) {
////            objectFactoryForInspectionPlanChangeNote.addInspectionPlanToChangeNote(productChangeNote);
////            generateProductChangeNotes();
////            submittedProductChangeNote.addTextToChangeDescription(submittedInspectionPlanChangeNote.getChangeDescription());
////        }
//
//        productChangeNotes.forEach(this::persistProductChangeNote);
//
//        return createCopyOfProductChangeNotes();
//    }
//
//
//    private void generateProductChangeNotes() {
//
//        Map<Product, InspectionPlanChangeNote> productsWithPlanChangeNotes = generateProductsWithPlanChangeNotesMap();
//
//        productsWithPlanChangeNotes.forEach((product, planChangeNote) -> {
//                    if (!product.equals(submittedProduct))
//                        productChangeNotes.add(createProductChangeNote(product, planChangeNote));
//                }
//        );
//    }
//
//
//    private Map<Product, InspectionPlanChangeNote> generateProductsWithPlanChangeNotesMap() {
//
//        return inspectionPlanChangeManager.processPlanChangeNote(
//                submittedProduct,
//                submittedInspectionPlanChangeNote,
//                new HashMap<>());
//    }
//
//
//    private ProductChangeNote createProductChangeNote(
//            Product product, InspectionPlanChangeNote planChangeNote) {
//
//        ProductChangeNote productChangeNote = new ProductChangeNote();
//
//        product.setRevision(product.getRevision() + 1);
//        product.setDateModified(planChangeNote.getDateCreated());
//
////        productChangeNote.addTextToChangeDescription(planChangeNote.getChangeDescription());
////        productChangeNote.setCreatorId(planChangeNote.getCreatedBy());
////        productChangeNote.setDateCreated(planChangeNote.getDateCreated());
//
//        productChangeNote.setProduct(product);
//        productChangeNote.setInspectionPlanChangeNote(planChangeNote);
//
//        return productChangeNote;
//    }
//
//
//    private void persistProductChangeNote(ProductChangeNote productChangeNote) {
//
//        Product managedProduct = persistProduct(productChangeNote.getProduct());
//        ArchivedProduct archivedProduct = archivedProductService.archiveProduct(managedProduct);
//
//        if ( submittedProductChangeNote.getInspectionPlanChangeNote() != null) {
//            archivedProduct.setArchivedInspectionPlan(productChangeNote.getInspectionPlanChangeNote().getArchivedInspectionPlan());
//        }
////        productChangeNote.setArchivedProduct(archivedProduct);
////        ProductChangeNote updatedProductChangeNote = productChangeNoteService.save(productChangeNote);
////        productChangeNote.setId(updatedProductChangeNote.getId());
//    }
//
//
//    private Product persistProduct(Product product) {
//
//        if (product.getId() == 0) {
//            productService.save(product);
//        } else {
//            productService.update(product);
//        }
//        return product;
//    }
//
//
//    private void initializeManagerVariables(ProductChangeNote productChangeNote) {
//        this.submittedInspectionPlanChangeNote = productChangeNote.getInspectionPlanChangeNote();
//        this.submittedProduct = productChangeNote.getProduct();
//        this.submittedProductChangeNote = productChangeNote;
//        this.productChangeNotes = new HashSet<>();
//    }
//
//
//    private List<ProductChangeNote> createCopyOfProductChangeNotes() {
//
//        List<ProductChangeNote> copy = productChangeNotes.stream().toList();
//
//        productChangeNotes.clear();
//
//        return copy;
//    }
}
