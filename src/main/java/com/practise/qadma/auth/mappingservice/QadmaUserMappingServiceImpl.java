package com.practise.qadma.auth.mappingservice;

import com.practise.qadma.auth.payload.QadmaUserDTO;
import com.practise.qadma.auth.service.QadmaUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QadmaUserMappingServiceImpl implements QadmaUserMappingService {

    private final ModelMapper modelMapper;
    private final QadmaUserService qadmaUserService;

    @Autowired
    public QadmaUserMappingServiceImpl(ModelMapper modelMapper, QadmaUserService qadmaUserService) {
        this.modelMapper = modelMapper;
        this.qadmaUserService = qadmaUserService;
    }

    @Override
    public Set<QadmaUserDTO> findUsersByIds(Set<Long> userIdSet) {
        return qadmaUserService.findUsersByIds(userIdSet).stream()
                .map(u -> modelMapper.map(u, QadmaUserDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<QadmaUserDTO> findUsersBySearchTerm(String searchTerm) {
        return qadmaUserService.findUsersBySearchTerm(searchTerm).stream()
                .map(u -> modelMapper.map(u, QadmaUserDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public QadmaUserDTO findUserById(long id) {
        return modelMapper.map( qadmaUserService.findUserById(id), QadmaUserDTO.class);
    }
}
