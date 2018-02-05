package com.umut.learnspringboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;

public class User {
    private final UUID userUid;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final Gender gender;
    private final Integer age;

    public User(@JsonProperty("userUid") UUID userUid,
                @JsonProperty("firstName") String firstName,
                @JsonProperty("lastName") String lastName,
                @JsonProperty("email") String email,
                @JsonProperty("gender") Gender gender,
                @JsonProperty("age") Integer age) {
        this.userUid = userUid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.age = age;
    }

    @JsonProperty("id")
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

    public static User newUser(UUID userUid, User user){
        return new User(userUid, user.getFirstName(), user.getLastName(), user.getEmail(), user.gender, user.getAge());
    }

    public int getYearOfBirth() {
        return LocalDate.now().minusYears(age).getYear();
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
