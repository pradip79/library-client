package com.testannotation.searchbooks;
import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class BookCatalogClientIntegrationTest {
    @Autowired
    private BookCatalogClient bookCatalogClient;
    private WireMockServer wireMockServer;

    @BeforeEach
    public void setUp(){
        wireMockServer = new WireMockServer(7071);
        wireMockServer.start();

        configureFor(7071);

        stubFor(get(urlEqualTo("/book-catalog/book/101201"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"isbn\":\"101201\",\"title\":\"The Alchemist\",\"author\":[\"Paolo Coelho\"]}")
                )
        );
    }

    @Test
    void getBookByISBN_shouldReturn_singleBook() {
        //Given
        String ISBN = "101201";

        //When
        ResponseEntity<Book> response = bookCatalogClient.getBookByIsbn(ISBN);

        //Then
        then(response.getStatusCode().value())
                .as("Status code correct")
                .isEqualTo(200);
        then(response.getBody().getTitle())
                .as("Title as expected")
                .isEqualTo("The Alchemist");
        then(response.getBody().getAuthor())
                .as("Authors as expected")
                .contains("Paolo Coelho");
    }

    @AfterEach
    public void tearDown(){
        wireMockServer.stop();
    }
}

