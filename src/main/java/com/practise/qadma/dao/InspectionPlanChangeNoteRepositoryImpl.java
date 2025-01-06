package com.practise.qadma.dao;

import com.practise.qadma.entity.ArchivedInspectionPlan;
import com.practise.qadma.entity.InspectionPlan;
import com.practise.qadma.entity.InspectionPlanChangeNote;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InspectionPlanChangeNoteRepositoryImpl implements InspectionPlanChangeNoteRepository {

    private final EntityManager entityManager;

    @Autowired
    public InspectionPlanChangeNoteRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public InspectionPlanChangeNote findById(long id) {

//        TODO
        return null;
    }

    @Override
    public InspectionPlanChangeNote save(InspectionPlanChangeNote changeNote) {
        entityManager.persist(changeNote);
        return changeNote;
    }
}


