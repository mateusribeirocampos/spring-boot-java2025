package com.campos.testspringboot.data.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class PersonUpdateDTO {

    @NotBlank(message = "First name is required")
    @Size(max = 80, message = "First name must not exceed 80 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 80, message = "Last name must not exceed 80 characters")
    private String lastName;

    @NotBlank(message = "Last name is required")
    @Size(max = 80, message = "Last name must not exceed 80 characters")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(max = 11, message = "Phone number must not exceed 11 characters")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(max = 20, message = "Password must not exceed 20 characters")
    private String password;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    @NotBlank(message = "Address is required")
    @Size(max = 120, message = "Address must not exceed 120 characters")
    private String address;

    @NotBlank(message = "State abbreviation is required")
    @Size(max = 2, message = "State abbreviation must not exceed 2 characters")
    private String state;

    @NotBlank(message = "Gender is required")
    @Size(max = 6, message = "Gender must not exceed 6 characters")
    private String gender;

    public PersonUpdateDTO(String firstName, String lastName, String email, String phoneNumber, String password, Date birthDate, String address, String state, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.birthDate = birthDate;
        this.address = address;
        this.state = state;
        this.gender = gender;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
