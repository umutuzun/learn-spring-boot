package com.umut.learnspringboot.model;

import java.util.UUID;

public class User {
    private UUID userUid;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private Integer age;

    public User(UUID userUid, String firstName, String lastName, String email, Gender gender, Integer age) {
        this.userUid = userUid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    public User() {
    }

    public UUID getUserUid() {
        return userUid;
    }

    public void setUserUid(UUID uuid) {
        this.userUid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userUid=" + userUid +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }

    public  enum Gender {
        MALE,
        FEMALE
    }
}
