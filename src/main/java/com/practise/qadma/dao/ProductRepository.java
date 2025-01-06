package com.practise.qadma.dao;

import com.practise.qadma.entity.Product;

import java.util.Optional;
import java.util.Set;

public interface ProductRepository {

    Optional<Product> findById(long id);

    Set<Product> getProductsByInspectionTemplateId(long id);

    Product findByInspectionPlanId(long inspectionPlanId);

    Product save(Product product);

    Product update(Product updatedProduct);
}
