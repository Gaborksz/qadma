package com.practise.qadma.dao;

import com.practise.qadma.entity.InspectionPlanChangeNote;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InspectionPlanChangeNoteRepositoryImpl implements InspectionPlanChangeNoteRepository {

    private final EntityManager entityManager;

    @Autowired
    public InspectionPlanChangeNoteRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Optional<InspectionPlanChangeNote> findById(long id) {

        return Optional.ofNullable(entityManager.find(InspectionPlanChangeNote.class, id));
   }

    @Override
    public InspectionPlanChangeNote save(InspectionPlanChangeNote changeNote) {
        entityManager.persist(changeNote);
        return changeNote;
    }
}


