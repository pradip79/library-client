package com.testannotation.libraryclient;

import java.util.Objects;

public class Book {
    private String genre;
    private String publisher;

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Book(String isbn, String title, String authors, String publisher, String genre) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.genre = genre;
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
        return "ISBN: "+this.isbn+", Title: "+this.title+", Authors: "+this.authors+", Genre: "+this.genre+", Publisher:" +this.publisher;
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
