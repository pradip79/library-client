package com.testannotation.libraryclient;

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
				.withBody("{\"isbn\":\"101201\",\"title\":\"Book1\",\"author\":\"Author1;Author2\"}")
			)
		);
	}

	@Test
	void testBookCatalog() {
		//Given
		String ISBN = "101201";

		//When
		ResponseEntity<Book> response = bookCatalogClient.getBookByIsbn(ISBN);

		//Then
		then(response.getStatusCode().equals(200));
		then(response.getBody().getIsbn().equals(ISBN));
		then(response.getBody().getTitle().equals("Book1"));
		then(response.getBody().getAuthors().equals("Author1;Author2"));

	}

	@AfterEach
	public void tearDown(){
		wireMockServer.stop();
	}

}
