package com.practise.qadma.dao;

import com.practise.qadma.entity.InspectionPlan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class InspectionPlanRepositoryImpl implements InspectionPlanRepository {

    private final EntityManager entityManager;


    @Autowired
    public InspectionPlanRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<InspectionPlan> findById(long id) {

        return Optional.ofNullable(entityManager.find(InspectionPlan.class, id));
    }


    @Override
    public InspectionPlan finByProductId(long productId) {

        TypedQuery<InspectionPlan> query = entityManager.createQuery("""
                SELECT p.inspectionPlan
                FROM Product p
                WHERE p.id = :productId
                """, InspectionPlan.class);

        query.setParameter("productId", productId);

        return query.getSingleResult();
    }


    @Override
    public InspectionPlan save(InspectionPlan inspectionPlan) {

        entityManager.persist(inspectionPlan);
        return inspectionPlan;
    }


    @Override
    public InspectionPlan update(InspectionPlan inspectionPlan) {

        entityManager.merge(inspectionPlan);
        return inspectionPlan;
    }
}
