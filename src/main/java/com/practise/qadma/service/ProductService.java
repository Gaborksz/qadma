package com.practise.qadma.service;

import com.practise.qadma.entity.Product;
import com.practise.qadma.payload.ProductSearchCriteriaDTO;
import com.practise.qadma.payload.view.ProductViewDTO;

import java.util.List;
import java.util.Set;

public interface ProductService {

    ProductViewDTO findById(long id);

    Set<Product> getProductsByInspectionTemplateId(long id);

    Product findByInspectionPlanId(long inspectionPlanId);

    Product save(Product product);

    Product update(Product updateProduct);

    List<Product> findAll();

    Set<ProductViewDTO> search(ProductSearchCriteriaDTO searchCriteria);

    Set<Product> populateUserFields(Set<Product> products);
}