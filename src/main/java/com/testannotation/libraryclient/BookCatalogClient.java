package com.testannotation.libraryclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Component
public class BookCatalogClient {
    private RestTemplate restTemplate;
    private final String bookCatalogServiceURL;

    @Autowired
    public BookCatalogClient(@Value("${book-service.url}") final String bookCatalogServiceURL) {
        System.out.println("Inside BookCatalogClient: " + bookCatalogServiceURL);
        this.bookCatalogServiceURL = bookCatalogServiceURL;
        restTemplate = new RestTemplate();
    }

    public ResponseEntity<Book> getBookByIsbn(String isbn) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(bookCatalogServiceURL)
                .path("/book-catalog/book/" + isbn);

        ResponseEntity<Book> responseEntity = restTemplate.getForEntity(builder.toUriString(), Book.class);
        return responseEntity;
    }

    public ResponseEntity<Book[]> getAllBooks() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(bookCatalogServiceURL)
                .path("/book-catalog/books");

        ResponseEntity<Book[]> responseEntity = restTemplate.getForEntity(builder.toUriString(), Book[].class);
        return responseEntity;
    }
}
