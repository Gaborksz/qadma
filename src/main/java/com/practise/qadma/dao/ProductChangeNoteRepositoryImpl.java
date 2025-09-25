package com.practise.qadma.dao;

import com.practise.qadma.entity.ProductChangeNote;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class ProductChangeNoteRepositoryImpl implements ProductChangeNoteRepository {

    private final EntityManager entityManager;


    @Autowired
    public ProductChangeNoteRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public ProductChangeNote save(ProductChangeNote productChangeNote) {

         return  entityManager.merge(productChangeNote);
    }

    @Override
    public Set<ProductChangeNote> getProductChangeNotes(Set<Long> ids) {

        TypedQuery<ProductChangeNote> query = entityManager.createQuery(
                """
                        SELECT pcn 
                        FROM ProductChangeNote pcn
                        WHERE pcn.id IN :ids  
                        """, ProductChangeNote.class);

        query.setParameter("ids", ids);

        return new HashSet<>(query.getResultList());
    }
}
