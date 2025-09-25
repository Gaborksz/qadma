package com.practise.qadma.auth.service;

import com.practise.qadma.auth.dao.QadmaUserRepository;
import com.practise.qadma.auth.entity.QadmaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class QadmaUserServiceImpl implements QadmaUserService{

    private final QadmaUserRepository qadmaUserRepository;

    @Autowired
    public QadmaUserServiceImpl(QadmaUserRepository qadmaUserRepository) {
        this.qadmaUserRepository = qadmaUserRepository;
    }

    @Override
    public Set<QadmaUser> findUsersByIds(Set<Long> userIdSet) {
        return qadmaUserRepository.findUsersByIds(userIdSet);
    }

    @Override
    public QadmaUser findUserById(long id){

        QadmaUser user = new QadmaUser();

        if ( id > 0) {
            user = qadmaUserRepository.findUserById(id);
        }
        return user != null ? user : new QadmaUser();
    }

    @Override
    public Set<QadmaUser> findUsersBySearchTerm(String searchTerm) {
        return qadmaUserRepository.findUsersBySearchTerm(searchTerm);
    }
}
