package com.practise.qadma.dao;

import com.practise.qadma.entity.Product;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private EntityManager entityManager;


    @Autowired
    public ProductRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> findAll() {

        return entityManager.createQuery("SELECT p FROM Product p",
                Product.class).getResultList();
    }

    @Override
    public void updateProduct(long id, Product product) {

        entityManager.merge(product);
    }
}
