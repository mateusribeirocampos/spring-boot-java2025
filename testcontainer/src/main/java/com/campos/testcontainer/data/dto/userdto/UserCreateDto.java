package com.campos.testcontainer.data.dto.userdto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class UserCreateDto {

    private String firstName;
    private String lastName;
    private String gender;
    private String email;

    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String password;
    private String address;
    private String state;

    public UserCreateDto() {}

    public UserCreateDto(String firstName, String lastName, String gender, String email, LocalDate birthDate, String phoneNumber, String password, String address, String state) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.state = state;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
