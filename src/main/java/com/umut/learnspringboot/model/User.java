package com.umut.learnspringboot.model;

import java.util.UUID;

public class User {
    private final UUID userUid;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Gender gender;
    private final Integer age;

    public User(UUID userUid, String firstName, String lastName, String email, Gender gender, Integer age) {
        this.userUid = userUid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    public UUID getUserUid() {
        return userUid;
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
