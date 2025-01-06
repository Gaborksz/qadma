package com.practise.qadma.dao;

import com.practise.qadma.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final EntityManager entityManager;


    @Autowired
    public ProductRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Optional<Product> findById(long id) {

        return Optional.ofNullable(entityManager.find(Product.class, id));
    }


    @Override
    public Product findByInspectionPlanId(long inspectionPlanId) {

        TypedQuery<Product> query = entityManager.createQuery("""
                SELECT p 
                FROM Product p
                WHERE p.inspectionPlan.id = :inspectionPlanId
                """, Product.class);

        query.setParameter("productId", inspectionPlanId);

        return query.getSingleResult();
    }

    @Override
    public Set<Product> getProductsByInspectionTemplateId(long id) {

        TypedQuery<Product> query = entityManager.createQuery("""
                SELECT p FROM Product p
                JOIN p.inspectionPlan ip 
                JOIN ip.templateSequence s
                WHERE s.id = :inspectionTemplateId                                                        
                """, Product.class);

        query.setParameter("inspectionTemplateId", id);

        Set<Product> a = new HashSet<>(query.getResultList());

        return a;
    }


    @Override
    public Product save(Product product) {

        entityManager.persist(product);

        return product;
    }


    @Override
    public Product update(Product updatedProduct) {

        entityManager.merge(updatedProduct);
        return updatedProduct;
    }
}
