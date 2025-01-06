package com.practise.qadma.supportclasses;

import com.practise.qadma.entity.InspectionTemplateChangeNote;
import com.practise.qadma.entity.Product;
import com.practise.qadma.exception.ItemNotFoundException;
import com.practise.qadma.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ObjectFactoryForInspectionTemplateChangeNote {

    private final ProductService productService;

    @Autowired
    public ObjectFactoryForInspectionTemplateChangeNote(ProductService productService) {
        this.productService = productService;
    }


    public void assignManagedProductsToTemplateChangeNote(
            InspectionTemplateChangeNote inspectionTemplateChangeNote
    ) {

        Set<Product> productsToUpdate = inspectionTemplateChangeNote.getProductsToUpdate();
        Set<Product> managedProducts = new HashSet<>();

        if (productsToUpdate != null && !productsToUpdate.isEmpty()) {
            productsToUpdate.forEach(product -> {

                Product mangedProduct = productService.findById(product.getId());
                if (mangedProduct == null) throw new ItemNotFoundException(product.getId(), "Product");

                managedProducts.add(mangedProduct);

            });
        }
        inspectionTemplateChangeNote.setProductsToUpdate(managedProducts);
    }
}
