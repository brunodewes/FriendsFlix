package com.friendsflix.domain.model;

import androidx.room.Entity;

@Entity
public class UserTest {
    private String name;
    private Integer age;

    public UserTest() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}