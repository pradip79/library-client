package com.testannotation.searchbooks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book {

    private String isbn;
    private String title;
    private List<String> author = new ArrayList<>();
    private String genre;
    private String publisher;

    public Book(){

    }
    public Book(String isbn, String title, List<String> author, String genre, String publisher) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
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

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "ISBN: "+this.isbn+", Title: "+this.title+", Authors: "+this.author +", Genre: "+this.genre+", Publisher:" +this.publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Book book = (Book) o;

        return Objects.equals(this.isbn, book.isbn) &&
                Objects.equals(this.title, book.title);
    }

}