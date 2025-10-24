package com.practise.qadma.auth.service;

import com.practise.qadma.auth.entity.QadmaUser;
import com.practise.qadma.exception.UserNotFoundException;

import java.util.Set;

public interface QadmaUserService {

    Set<QadmaUser> findUsersByIds(Set<Long> userIdSet);

    QadmaUser findUserById(long id) throws UserNotFoundException;

    Set<QadmaUser> findUsersBySearchTerm(String searchTerm);
}
