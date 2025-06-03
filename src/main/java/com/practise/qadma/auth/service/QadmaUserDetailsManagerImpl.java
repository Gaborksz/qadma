package com.practise.qadma.auth.service;

import com.practise.qadma.auth.dao.QadmaUserRepository;
import com.practise.qadma.auth.entity.QadmaUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QadmaUserDetailsManagerImpl implements UserDetailsManager {

    private final QadmaUserRepository userDetailsRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Autowired
    public QadmaUserDetailsManagerImpl(QadmaUserRepository userDetailsRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userDetailsRepository = userDetailsRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsRepository.loadUserByUsername(username);
    }

    @Override
    @Transactional(transactionManager = "defaultSpringUsersTransactionManager")
    public void createUser(UserDetails user) {

        QadmaUser newUser = (QadmaUser) user;
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userDetailsRepository.createUser(user);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return userDetailsRepository.userExists(username);
    }
}
