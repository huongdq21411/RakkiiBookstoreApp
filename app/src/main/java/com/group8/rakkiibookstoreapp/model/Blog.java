package com.group8.rakkiibookstoreapp.model;

public class Blog {
    int blogPhoto;
    String blogTitle;
    String blogDescription;
    Double blogView;

    public Blog(int blogPhoto, String blogTitle, String blogDescription, Double blogView) {
        this.blogPhoto = blogPhoto;
        this.blogTitle = blogTitle;
        this.blogDescription = blogDescription;
        this.blogView = blogView;
    }

    public int getBlogPhoto() {
        return blogPhoto;
    }

    public void setBlogPhoto(int blogPhoto) {
        this.blogPhoto = blogPhoto;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogDescription() {
        return blogDescription;
    }

    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }

    public Double getBlogView() {
        return blogView;
    }

    public void setBlogView(Double blogView) {
        this.blogView = blogView;
    }
}
