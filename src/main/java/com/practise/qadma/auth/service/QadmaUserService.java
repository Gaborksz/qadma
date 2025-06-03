package com.practise.qadma.auth.service;

import com.practise.qadma.auth.entity.QadmaUser;

import java.util.Set;

public interface QadmaUserService {

    Set<QadmaUser> findUsersByIds(Set<Long> userIdSet);

    Set<QadmaUser> findUsersBySearchTerm(String searchTerm);
}
