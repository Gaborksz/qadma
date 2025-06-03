package com.practise.qadma.auth.entity;

import lombok.Getter;

@Getter
public enum QadmaUserAuthorityType {
    BASIC(1, "ADMIN"),
    MANAGER(2, "MANAGER"),
    ADMIN(3, "ADMIN"),
    EMPLOYEE(4, "EMPLOYEE");

    private final int id;
    private final String description;

    QadmaUserAuthorityType(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
