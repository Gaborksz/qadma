package com.practise.qadma.dao;

import com.practise.qadma.entity.InspectionPlanChangeNote;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InspectionPlanChangeNoteRepositoryImpl implements InspectionPlanChangeNoteRepository {

    private EntityManager entityManager;

    @Autowired
    public InspectionPlanChangeNoteRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public InspectionPlanChangeNote save(InspectionPlanChangeNote inspectionPlanChangeNote) {

        entityManager.persist(inspectionPlanChangeNote);

        return inspectionPlanChangeNote;
    }
}


