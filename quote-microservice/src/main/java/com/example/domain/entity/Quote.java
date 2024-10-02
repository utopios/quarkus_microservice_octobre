package com.example.domain.entity;

public class Quote {

    private Long id;
    private Long authorId;

    private String content;


    public Quote() {
    }

    public Quote(Long authorId, String content) {
        this.authorId = authorId;
        this.content = content;
    }

    public Quote(Long id, Long authorId, String content) {
        this(authorId, content);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
