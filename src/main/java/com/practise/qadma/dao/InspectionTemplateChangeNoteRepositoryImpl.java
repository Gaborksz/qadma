package com.practise.qadma.dao;

import com.practise.qadma.entity.InspectionTemplateChangeNote;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class InspectionTemplateChangeNoteRepositoryImpl implements InspectionTemplateChangeNoteRepository{

    private final EntityManager entityManager;

    @Autowired
    public InspectionTemplateChangeNoteRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public InspectionTemplateChangeNote save(InspectionTemplateChangeNote changeNote) {
        return entityManager.merge(changeNote);
    }
}
