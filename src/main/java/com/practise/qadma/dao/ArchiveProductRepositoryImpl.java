package com.practise.qadma.dao;

import com.practise.qadma.entity.ArchivedProduct;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArchiveProductRepositoryImpl implements ArchiveProductRepository {

    private final EntityManager entityManager;

    @Autowired
    public ArchiveProductRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public ArchivedProduct save(ArchivedProduct archivedProduct) {
        entityManager.persist(archivedProduct);
        return archivedProduct;
    }
}
