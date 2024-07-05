package com.practise.qadma.dao;

import com.practise.qadma.entity.InspectionPlan;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class InspectionPlanRepositoryImpl implements InspectionPlanRepository {

    private EntityManager entityManager;


    @Autowired
    public InspectionPlanRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<InspectionPlan> findById(long id) {

        return Optional.ofNullable(entityManager.find(InspectionPlan.class, id));
    }

    @Override
    public InspectionPlan save(InspectionPlan inspectionPlan) {

        entityManager.persist(inspectionPlan);
        return inspectionPlan;
    }

    @Override
    public InspectionPlan update(InspectionPlan inspectionPlan) {

        return entityManager.merge(inspectionPlan);
    }
}
