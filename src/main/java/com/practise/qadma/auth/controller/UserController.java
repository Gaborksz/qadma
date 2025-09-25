package com.practise.qadma.auth.controller;

import com.practise.qadma.auth.mappingservice.QadmaUserMappingService;
import com.practise.qadma.auth.payload.QadmaUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController()
@RequestMapping("api/user")
public class UserController {

    private final QadmaUserMappingService qadmaUserMappingService;

    @Autowired
    public UserController(QadmaUserMappingService qadmaUserMappingService) {
        this.qadmaUserMappingService = qadmaUserMappingService;
    }

    @PostMapping("ids")
    public ResponseEntity<Set<QadmaUserDTO>> findUsersByIds(@RequestBody Set<Long> userIdSet) {
        return ResponseEntity.ok(qadmaUserMappingService.findUsersByIds(userIdSet));
    }


    @PostMapping("searchterm")
    public ResponseEntity<Set<QadmaUserDTO>> findUsersBySearchTerm(@RequestBody String searchTerm) {

        return ResponseEntity.ok(qadmaUserMappingService.findUsersBySearchTerm(searchTerm));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QadmaUserDTO> findUserById(@PathVariable long id) {
        return ResponseEntity.ok(qadmaUserMappingService.findUserById(id));
    }
}
