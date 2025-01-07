package com.practise.qadma.dao;

import com.practise.qadma.entity.ProductChangeNote;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductChangeNoteRepositoryImpl implements ProductChangeNoteRepository {

    private final EntityManager entityManager;


    @Autowired
    public ProductChangeNoteRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ProductChangeNote save(ProductChangeNote productChangeNote) {

        entityManager.persist(productChangeNote);

        return productChangeNote;
    }

    @Override
    public Optional<ProductChangeNote> getProductChangeNote(long id) {

        return Optional.ofNullable(entityManager.find(ProductChangeNote.class, id));
    }
}
