package com.practise.qadma.enums;

public enum DateFilterSelector {
    NONE("NONE"),
    ON("ON"),
    BETWEEN("BETWEEN"),
    BEFORE("BEFORE"),
    AFTER("AFTER");

    private final String value;

    DateFilterSelector(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}