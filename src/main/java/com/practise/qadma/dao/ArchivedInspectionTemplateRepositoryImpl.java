package com.practise.qadma.dao;

import com.practise.qadma.entity.ArchivedInspectionTemplate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class ArchivedInspectionTemplateRepositoryImpl implements ArchivedInspectionTemplateRepository {

    private final EntityManager entityManager;

    @Autowired
    public ArchivedInspectionTemplateRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ArchivedInspectionTemplate archiveTemplate(ArchivedInspectionTemplate inspectionTemplate) {

        entityManager.persist(inspectionTemplate);

        return inspectionTemplate;
    }

    @Override
    public ArchivedInspectionTemplate getLatestArchiveForInspectionTemplate(long id) {

        TypedQuery<ArchivedInspectionTemplate> query = entityManager.createQuery("""
                SELECT cn.archivedInspectionTemplate
                FROM InspectionTemplateChangeNote cn
                WHERE cn.inspectionTemplate.id = :inspectionTemplateId
                ORDER BY cn.dateCreated DESC
                LIMIT 1
                """, ArchivedInspectionTemplate.class);

        query.setParameter("inspectionTemplateId", id);

        return query.getSingleResult();
    }
}
