package com.campos.testspringboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "person_tb")
public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @NotBlank(message = "First name is required")
    @Size(max = 80, message = "First name must not exceed 80 characters")
    @Column(name = "firstName")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 80, message = "Last name must not exceed 80 characters")
    @Column(name = "lastName")
    private String lastName;

    @NotBlank(message = "Last name is required")
    @Size(max = 80, message = "Last name must not exceed 80 characters")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(max = 11, message = "Phone number must not exceed 11 characters")
    @Column(name = "phoneNumber")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(max = 20, message = "Password must not exceed 20 characters")
    @Column(name = "password")
    private String password;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birthDate", nullable = false)
    private Date birthDate;

    @NotBlank(message = "Address is required")
    @Size(max = 120, message = "Address must not exceed 120 characters")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "State abbreviation is required")
    @Size(max = 2, message = "State abbreviation must not exceed 2 characters")
    @Column(name = "state")
    private String state;

    @NotBlank(message = "Gender is required")
    @Size(max = 6, message = "Gender must not exceed 6 characters")
    @Column(name = "gender")
    private String gender;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public Person() {
    }

    public Person(UUID id, String firstName, String lastName, String email, String phoneNumber, String password, Date birthDate, String address, String state, String gender, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.birthDate = birthDate;
        this.address = address;
        this.state = state;
        this.gender = gender;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person person)) return false;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(email, person.email) && Objects.equals(phoneNumber, person.phoneNumber) && Objects.equals(password, person.password) && Objects.equals(birthDate, person.birthDate) && Objects.equals(address, person.address) && Objects.equals(state, person.state) && Objects.equals(gender, person.gender) && Objects.equals(createdAt, person.createdAt) && Objects.equals(updatedAt, person.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phoneNumber, password, birthDate, address, state, gender, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", gender='" + gender + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}