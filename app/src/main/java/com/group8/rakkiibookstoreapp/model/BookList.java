package com.group8.rakkiibookstoreapp.model;

import java.io.Serializable;
import java.util.Objects;

public class BookList implements Serializable {
    private int numberInCart;
    private String category;
    private String author;
    private String publisher;
    private String title;
    private String picUrl;
    private int review;
    private double score;
    private double price;
    private String description;
    private boolean isLiked;


    public BookList(int numberInCart, String category, String author, String publisher, String title, String picUrl, int review, double score, double price, String description) {
        this.numberInCart = numberInCart;
        this.category = category;
        this.author = author;
        this.publisher = publisher;
        this.title = title;
        this.picUrl = picUrl;
        this.review = review;
        this.score = score;
        this.price = price;
        this.description = description;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookList bookList = (BookList) o;
        return Objects.equals(title, bookList.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
