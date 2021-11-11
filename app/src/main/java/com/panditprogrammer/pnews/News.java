package com.panditprogrammer.pnews;

public class News {
    private String description;
    private String title;
    private String author;
    private String newsUrl;
    private String imageUrl;

    public News(){}

    public News(String description, String title, String author, String newsUrl, String imageUrl) {
        this.description = description;
        this.title = title;
        this.author = author;
        this.newsUrl = newsUrl;
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

