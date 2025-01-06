package com.practise.qadma.service;

import com.practise.qadma.entity.Product;

import java.util.Set;

public interface ProductService {

    Product findById(long id);

    Set<Product> getProductsByInspectionTemplateId(long id);

    Product findByInspectionPlanId(long inspectionPlanId);

    Product save(Product product);

    Product update(Product updateProduct);
}
