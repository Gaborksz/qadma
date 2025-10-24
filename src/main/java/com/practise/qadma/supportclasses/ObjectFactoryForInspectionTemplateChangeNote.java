package com.practise.qadma.supportclasses;

import com.practise.qadma.entity.InspectionTemplateChangeNote;
import com.practise.qadma.entity.Product;
import com.practise.qadma.exception.ItemNotFoundException;
import com.practise.qadma.payload.view.ProductViewDTO;
import com.practise.qadma.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ObjectFactoryForInspectionTemplateChangeNote {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public ObjectFactoryForInspectionTemplateChangeNote(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }


    public void assignManagedProductsToTemplateChangeNote(
            InspectionTemplateChangeNote inspectionTemplateChangeNote
    ) {

        Set<Product> productsToUpdate = inspectionTemplateChangeNote.getProductsToUpdate();
        Set<Product> managedProducts = new HashSet<>();

        if (productsToUpdate != null && !productsToUpdate.isEmpty()) {
            productsToUpdate.forEach(product -> {

                ProductViewDTO productDto =  productService.findById(product.getId());
                Product managedProduct = modelMapper.map(productDto, Product.class);
                if (managedProduct == null) throw new ItemNotFoundException(product.getId(), "Product");

                managedProducts.add(managedProduct);

            });
        }
        inspectionTemplateChangeNote.setProductsToUpdate(managedProducts);
    }
}
