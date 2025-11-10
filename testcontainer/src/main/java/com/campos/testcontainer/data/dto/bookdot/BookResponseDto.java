package com.campos.testcontainer.data.dto.bookdot;

import com.campos.testcontainer.data.dto.userdto.UserSummaryDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.List;

public class BookResponseDto {

    private Long id;

    private List<UserSummaryDto> author;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "launch_date")
    private LocalDateTime launchDate;

    private Double price;
    private String title;
    private String description;

    public BookResponseDto() {}

    public BookResponseDto(Long id, List<UserSummaryDto> author, LocalDateTime launchDate, Double price, String title, String description) {
        this.id = id;
        this.author = author;
        this.launchDate = launchDate;
        this.price = price;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserSummaryDto> getAuthor() {
        return author;
    }

    public void setAuthor(List<UserSummaryDto> author) {
        this.author = author;
    }

    public LocalDateTime getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDateTime launchDate) {
        this.launchDate = launchDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}



