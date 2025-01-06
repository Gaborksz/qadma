package com.practise.qadma.dao;

import com.practise.qadma.entity.InspectionTemplate;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InspectionTemplateRepositoryImpl implements InspectionTemplateRepository {

    private final EntityManager entityManager;


    @Autowired
    public InspectionTemplateRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<InspectionTemplate> findById(long id) {

        return Optional.ofNullable(entityManager.find(InspectionTemplate.class, id));
    }

    @Override
    public InspectionTemplate save(InspectionTemplate inspectionTemplate) {

        entityManager.persist(inspectionTemplate);
        return inspectionTemplate;
    }

    @Override
    public InspectionTemplate update(InspectionTemplate inspectionTemplate) {
        return entityManager.merge(inspectionTemplate);
    }
}
