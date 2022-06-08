package com.codegym.cms.model;

import javax.persistence.*;

@Entity
@Table(name = "form")
public class Form {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String bookName;
    private int code;

    public Form() {
    }

    public Form(String bookName, int code) {
        this.bookName = bookName;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
