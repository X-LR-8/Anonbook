package org.example;

import jakarta.persistence.*;

import java.util.List;


@Entity(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="date")
    private Long date;
    @Column(name="text")
    private String text;
    @Column(name="imgurl")
    private String imgurl;
    @Column(name="comments")
    private String comments;

    public Post(int id, Long date, String text, String imgurl, String comments) {
        this.id = id;
        this.date = date;
        this.text = text;
        this.imgurl = imgurl;
        this.comments = comments;
    }

    public Post(Long date, String text, String imgurl, String comments) {
        this.date = date;
        this.text = text;
        this.imgurl = imgurl;
        this.comments = comments;
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
