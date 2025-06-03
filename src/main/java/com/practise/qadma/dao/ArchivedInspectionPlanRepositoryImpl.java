package com.practise.qadma.dao;

import com.practise.qadma.entity.ArchivedInspectionPlan;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ArchivedInspectionPlanRepositoryImpl implements ArchivedInspectionPlanRepository{

    private final EntityManager entityManager;

    @Autowired
    public ArchivedInspectionPlanRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public ArchivedInspectionPlan save(ArchivedInspectionPlan archivedInspectionPlan) {

        entityManager.persist(archivedInspectionPlan);

        return archivedInspectionPlan;
    }
}
