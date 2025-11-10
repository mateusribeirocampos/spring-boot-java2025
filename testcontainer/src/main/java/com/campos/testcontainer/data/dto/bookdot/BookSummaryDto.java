package com.campos.testcontainer.data.dto.bookdot;

public class BookSummaryDto {

    private Long id;
    private String author;
    private Double price;
    private String title;

    public BookSummaryDto() {}

    public BookSummaryDto(Long id, String author, Double price, String title) {
        this.id = id;
        this.author = author;
        this.price = price;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
}
