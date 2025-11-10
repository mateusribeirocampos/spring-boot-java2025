package com.campos.testcontainer.data.dto.bookdot;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.List;

public class BookUpdateDto {

    private Double price;
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "launch_date")
    private LocalDateTime launchDate;

    private String description;

    private List<Long> authorsIds;

    public BookUpdateDto() {}

    public BookUpdateDto(Double price, String title, LocalDateTime launchDate, String description, List<Long> authorsIds) {
        this.price = price;
        this.title = title;
        this.launchDate = launchDate;
        this.description = description;
        this.authorsIds = authorsIds;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDateTime launchDate) {
        this.launchDate = launchDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getAuthorsIds() {
        return authorsIds;
    }

    public void setAuthorsIds(List<Long> authorsIds) {
        this.authorsIds = authorsIds;
    }
}
