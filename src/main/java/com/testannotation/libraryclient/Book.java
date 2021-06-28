package com.testannotation.libraryclient;

import java.util.Objects;

public class Book {
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    private String isbn;
    private String title;
    private String authors;

    public Book(){

    }
    public Book(String isbn, String title, String authors) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;

    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        return "ISBN: "+this.isbn+" ,Title: "+this.title+" ,Authors: "+this.authors;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        Book book = (Book) o;

        return Objects.equals(this.isbn, book.isbn) &&
                Objects.equals(this.title, book.title) &&
                Objects.equals(this.authors, book.authors);

    }
}
