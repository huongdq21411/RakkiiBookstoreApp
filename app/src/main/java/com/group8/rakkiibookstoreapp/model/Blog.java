package com.group8.rakkiibookstoreapp.model;

public class Blog {
    private int blogPhoto;
    private int blogId;
    private String blogTitle;
    private String blogDescription;
    private Double blogView;
    String blogTime;
    String blogContent;

    public Blog(int blogId) {
        this.blogId = blogId;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getBlogTime() {
        return blogTime;
    }

    public void setBlogTime(String blogTime) {
        this.blogTime = blogTime;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public Blog(int blogPhoto, int blogId, String blogTitle, String blogDescription, Double blogView, String blogTime) {
        this.blogPhoto = blogPhoto;
        this.blogId = blogId;
        this.blogTitle = blogTitle;
        this.blogDescription = blogDescription;
        this.blogView = blogView;
        this.blogTime = blogTime;
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
