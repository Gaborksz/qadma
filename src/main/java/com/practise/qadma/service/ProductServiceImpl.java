package com.practise.qadma.service;

import com.practise.qadma.dao.ProductRepository;
import com.practise.qadma.entity.Product;
import com.practise.qadma.exception.ItemNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product findById(long id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id, "Product"));
    }


    @Override
    public Product findByInspectionPlanId(long inspectionPlanId) {
        return productRepository.findByInspectionPlanId(inspectionPlanId);
    }


    @Override
    public Set<Product> getProductsByInspectionTemplateId(long id) {
        return productRepository.getProductsByInspectionTemplateId(id);
    }


    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }


    @Transactional
    @Override
    public Product update(Product updatedProduct) {
        return productRepository.update(updatedProduct);
    }
}
