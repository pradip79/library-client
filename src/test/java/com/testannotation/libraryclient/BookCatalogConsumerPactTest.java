package com.testannotation.libraryclient;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@PactTestFor(providerName = "book-catalog", port = "7071")
@ExtendWith(PactConsumerTestExt.class)
public class BookCatalogConsumerPactTest {
    BookCatalogClient bookCatalogClient;

    @Pact(consumer = "search-books", provider = "book-catalog")
    RequestResponsePact getAllBooks(PactDslWithProvider pactBuilder){
        return pactBuilder.given("books exist")
                .uponReceiving("get all books")
                .method("GET")
                .path("/book-catalog/books")
                .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", "application/json"))
                .body(PactDslJsonArray
                        .arrayEachLike(3)
                        .stringType("isbn", "101201")
                        .stringType("title", "The Alchemist")
                        .stringType("authors", "Paolo Coelho")
                        .stringType("publisher", "HarperCollins")
                        .stringType("genre", "Fiction"))
                .toPact();
    }

    @Pact(consumer = "search-books", provider = "book-catalog")
    RequestResponsePact getOneBook(PactDslWithProvider pactBuilder){
        return pactBuilder.given("book with ISBN 101201 available")
                .uponReceiving("get one book")
                .method("GET")
                .path("/book-catalog/book/101201")
                .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", "application/json"))
                .body(new PactDslJsonBody()
                        .stringType("isbn", "101201")
                        .stringType("title", "The Alchemist")
                        .stringType("authors", "Paolo Coelho")
                        .stringType("publisher", "HarperCollins")
                        .stringType("genre", "Fiction"))
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "getAllBooks")
    void getAllBooks_whenBookExists(MockServer mockServer){
        bookCatalogClient = new BookCatalogClient(mockServer.getUrl());
        List<Book> expectedBooks = List.of(new Book("101201", "The Alchemist", "Paolo Coelho", "Fiction", "HarperCollins"),
                new Book("101201", "The Alchemist", "Paolo Coelho", "Fiction", "HarperCollins"),
                new Book("101201", "The Alchemist", "Paolo Coelho", "Fiction", "HarperCollins"));

        List<Book> actualBooks = Arrays.asList(bookCatalogClient.getAllBooks().getBody());

        assertEquals(expectedBooks, actualBooks);
    }

    @Test
    @PactTestFor(pactMethod = "getOneBook")
    void getBookByIsbn_whenBookExists(MockServer mockServer){
        bookCatalogClient = new BookCatalogClient(mockServer.getUrl());
        Book expectedBook = new Book("101201", "The Alchemist", "Paolo Coelho", "Fiction", "HarperCollins");
        Book actualBook = bookCatalogClient.getBookByIsbn("101201").getBody();
        assertEquals(expectedBook, actualBook);
    }
}
