package com.practise.qadma.auth.dao;

import com.practise.qadma.auth.entity.QadmaUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class QadmaJdbcUserRepositoryImpl implements QadmaUserRepository {

    private EntityManager entityManager;

    @Autowired
    public QadmaJdbcUserRepositoryImpl(@Qualifier("defaultSpringUsersEntityManagerFactory") EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public QadmaUser loadUserByUsername(String username) {

        TypedQuery<QadmaUser> query = entityManager.createQuery("""
                SELECT u FROM QadmaUser u 
                WHERE u.username= :userName
                """, QadmaUser.class);

        query.setParameter("userName", username);

        return query.getSingleResult();
    }

    @Override
    public void createUser(UserDetails user) {

        QadmaUser qUser = (QadmaUser) user;
        entityManager.persist(qUser);
    }

    @Override
    public void updateUser(QadmaUser user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {

        QadmaUser user = null;

        try {
            user = loadUserByUsername(username);
        } catch (Exception ignored) {
        }

        return user != null;
    }

    @Override
    public Set<QadmaUser> findUsersByIds(Set<Long> userIdSet) {

        TypedQuery<QadmaUser> query = entityManager.createQuery("""
                SELECT u FROM QadmaUser u WHERE u.id IN :ids
                """, QadmaUser.class);
        query.setParameter("ids", userIdSet);

        return new HashSet<>(query.getResultList());
    }

    @Override
    public Set<QadmaUser> findUsersBySearchTerm(String searchTerm) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<QadmaUser> query = cb.createQuery(QadmaUser.class);
        Root<QadmaUser> root = query.from(QadmaUser.class);
        List<Predicate> predicates = new ArrayList<>();

        if (searchTerm != null && !searchTerm.isEmpty()) {
            Predicate userName = cb.like(root.get("username"), "%" + searchTerm + "%");
            predicates.add(userName);
        }

        query.select(root).where(cb.and(predicates.toArray(new Predicate[0])));

        return new HashSet<>(entityManager.createQuery(query).getResultList());
    }
}
