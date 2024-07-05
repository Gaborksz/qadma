package com.practise.qadma.dao;

import com.practise.qadma.entity.InspectionTemplate;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InspectionTemplateRepositoryImpl implements InspectionTemplateRepository {

    private EntityManager entityManager;


    @Autowired
    public InspectionTemplateRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<InspectionTemplate> findById(long id) {

        return Optional.ofNullable(entityManager.find(InspectionTemplate.class, id));
    }

    @Override
    public InspectionTemplate saveNewOrGetExisting(InspectionTemplate inspectionTemplate) {

        Optional<InspectionTemplate> managedTemplate = findById(inspectionTemplate.getId());

        if (managedTemplate.isPresent()) return managedTemplate.get();

        entityManager.persist(inspectionTemplate);
        return inspectionTemplate;
    }
}
