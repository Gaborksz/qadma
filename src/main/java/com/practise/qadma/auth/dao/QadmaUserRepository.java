package com.practise.qadma.auth.dao;

import com.practise.qadma.auth.entity.QadmaUser;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

public interface QadmaUserRepository {

    QadmaUser loadUserByUsername(String username);

    void createUser(UserDetails user);

    void updateUser(QadmaUser user);

    void deleteUser(String username);

    void changePassword(String oldPassword, String newPassword);

    boolean userExists(String username);

    Set<QadmaUser> findUsersByIds(Set<Long> userIdSet);

    Set<QadmaUser> findUsersBySearchTerm(String searchTerm);
}
