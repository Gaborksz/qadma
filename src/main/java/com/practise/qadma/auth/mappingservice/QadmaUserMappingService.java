package com.practise.qadma.auth.mappingservice;

import com.practise.qadma.auth.entity.QadmaUser;
import com.practise.qadma.auth.payload.QadmaUserDTO;

import java.util.Set;

public interface QadmaUserMappingService {
    Set<QadmaUserDTO> findUsersByIds(Set<Long> userIdSet);

    Set<QadmaUserDTO> findUsersBySearchTerm(String searchTerm);

    QadmaUserDTO findUserById(long id);
}
