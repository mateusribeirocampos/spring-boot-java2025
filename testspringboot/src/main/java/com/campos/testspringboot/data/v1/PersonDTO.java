package com.campos.testspringboot.data.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class PersonDTO {

    private UUID id;

    @NotBlank(message = "First name is required")
    @Size(max = 80, message = "First name must not exceed 80 characters")
    private String firstName;

    @NotBlank(message = "Last is required")
    @Size(max = 80, message = "Last name must not exceed 80 characters")
    private String lastName;

    @NotBlank(message = "The state abbreviation is required")
    @Size(max = 2, message = "the state abbreviation must not exceed 2 characters")
    private String state;

    @NotBlank(message = "The date abbreviation is required")
    @Size(max = 19, message = "the state abbreviation must not exceed 19 characters")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    public PersonDTO() {}

    public PersonDTO(UUID id, String firstName, String lastName, String state, LocalDateTime createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.state = state;
        this.createdAt = createdAt;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PersonDTO personDTO)) return false;
        return Objects.equals(id, personDTO.id) && Objects.equals(firstName, personDTO.firstName) && Objects.equals(lastName, personDTO.lastName) && Objects.equals(state, personDTO.state) && Objects.equals(createdAt, personDTO.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, state, createdAt);
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", state='" + state + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
