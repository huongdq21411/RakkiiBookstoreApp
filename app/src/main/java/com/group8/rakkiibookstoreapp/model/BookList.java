package com.group8.rakkiibookstoreapp.model;

import java.io.Serializable;

public class BookList implements Serializable {
    private int numberInChart;
    private String category;
    private String title;
    private String picUrl;
    private int review;
    private double score;
    private double price;
    private String description;

    public BookList(int numberInChart, String category, String title, String picUrl, int review, double score, double price, String description) {
        this.numberInChart = numberInChart;
        this.category = category;
        this.title = title;
        this.picUrl = picUrl;
        this.review = review;
        this.score = score;
        this.price = price;
        this.description = description;
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

    public int getNumberInChart() {
        return numberInChart;
    }

    public void setNumberInChart(int numberInChart) {
        this.numberInChart = numberInChart;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}