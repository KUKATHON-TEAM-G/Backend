package com.kukathon.teamg.domain.member.entity;

public enum Gender {
    MALE,
    FEMALE,
    EXTRA;

    public Gender of(String gender) {
        return Gender.valueOf(gender);
    }
}
