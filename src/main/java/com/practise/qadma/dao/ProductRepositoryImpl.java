package com.practise.qadma.dao;

import com.practise.qadma.entity.Product;
import com.practise.qadma.enums.DateFilterSelector;
import com.practise.qadma.payload.ProductSearchCriteriaDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.*;

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

        query.setParameter("inspectionPlanId", inspectionPlanId);

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

        return   entityManager.merge(product);
    }


    @Override
    public Product update(Product updatedProduct) {

        entityManager.merge(updatedProduct);
        return updatedProduct;
    }

    @Override
    public List<Product> findAll() {

        TypedQuery<Product> query = entityManager.createQuery("""
                SELECT p FROM Product p
                """, Product.class);

        return query.getResultList();
    }

    @Override
    public Set<Product> search(ProductSearchCriteriaDTO productSearchCriteria) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();

        if (productSearchCriteria.getPartNumber() > 0) {
            Predicate partNumber = cb.equal(root.get("partNumber"), productSearchCriteria.getPartNumber());
            predicates.add(partNumber);
        }

        if (StringUtils.hasText(productSearchCriteria.getProductName())) {
            Predicate productName = cb.like(root.get("productName"), productSearchCriteria.getProductName() + "%");
            predicates.add(productName);
        }

        if (productSearchCriteria.getRevision() > 0) {
            Predicate revision = cb.equal(root.get("revision"), productSearchCriteria.getRevision());
            predicates.add(revision);
        }


        if (shouldApplyDateFilter(productSearchCriteria.getDateCreatedSelector())) {
            Predicate predicate = buildDatePredicate(
                    productSearchCriteria.getDateCreatedSelector(),
                    productSearchCriteria.getDateCreatedFrom(),
                    productSearchCriteria.getDateCreatedTo(),
                    cb, root, "dateCreated");
            if (predicate != null) predicates.add(predicate);
        }

        if (shouldApplyDateFilter(productSearchCriteria.getDateModifiedSelector())) {
            Predicate predicate = buildDatePredicate(
                    productSearchCriteria.getDateModifiedSelector(),
                    productSearchCriteria.getDateModifiedFrom(),
                    productSearchCriteria.getDateModifiedTo(),
                    cb, root, "dateModified");
            if (predicate != null) predicates.add(predicate);
        }

        if (productSearchCriteria.getCreatedBy() != null && productSearchCriteria.getCreatedBy().getId() > 0) {
            Predicate createdBy = cb.equal(root.get("creatorId"), productSearchCriteria.getCreatedBy().getId());
            predicates.add(createdBy);
        }

        if (productSearchCriteria.getModifiedBy() != null && productSearchCriteria.getModifiedBy().getId() > 0) {
            Predicate modifiedBy = cb.equal(root.get("modifierId"), productSearchCriteria.getModifiedBy().getId());
            predicates.add(modifiedBy);
        }

        query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));

        return new HashSet<>(entityManager.createQuery(query).getResultList());
    }

    private static boolean shouldApplyDateFilter(DateFilterSelector selector) {
        boolean applyFilter = false;

        if (selector != null) {
            applyFilter = selector != DateFilterSelector.NONE;
        }
        return applyFilter;
    }

    private static Predicate buildDatePredicate(DateFilterSelector selector,
                                                Date dateFrom,
                                                Date dateTo,
                                                CriteriaBuilder cb,
                                                Root<Product> root,
                                                String fieldName) {

        if (dateFrom != null) {
            if (selector == DateFilterSelector.ON) {
                return cb.equal(root.get(fieldName), dateFrom);
            }

            if (selector == (DateFilterSelector.BEFORE)) {
                return cb.lessThan(root.get(fieldName), dateFrom);
            }

            if (selector == (DateFilterSelector.AFTER)) {
                return cb.greaterThan(root.get(fieldName), dateFrom);
            }

            if (selector == (DateFilterSelector.BETWEEN)) {
                if (dateTo == null) return null;
                return cb.between(root.get(fieldName), dateFrom, dateTo);
            }
        }
        return null;
    }
}
