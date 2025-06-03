package com.practise.qadma.dao;

import com.practise.qadma.entity.Product;
import com.practise.qadma.payload.ProductSearchCriteriaDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository {

    Optional<Product> findById(long id);

    Set<Product> getProductsByInspectionTemplateId(long id);

    Product findByInspectionPlanId(long inspectionPlanId);

    Product save(Product product);

    Product update(Product updatedProduct);

    List<Product> findAll();

    Set<Product> search (ProductSearchCriteriaDTO productSearchCriteria);
}
