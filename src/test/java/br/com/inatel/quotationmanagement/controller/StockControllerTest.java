package br.com.inatel.quotationmanagement.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import br.com.inatel.quotationmanagement.controller.form.StockQuoteForm;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class StockControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void shouldReturn200ByListingAllTheStockQuotes() {
		webTestClient.get()
		.uri("/stock")
		.exchange()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectStatus().isOk();
	}
	
	@Test
	void shouldReturn200BySearchingByAValidStockId() {
		webTestClient.get()
		.uri("/stock/petr4")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk();
	}
	
	@Test
	void shouldReturn404BySearchingByAInvalidStockId() {
		webTestClient.get()
		.uri("/stock/mlgu4")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isNotFound();
	}

	@Test
	void shouldReturn201ByCreatingAStockQuote() {
		StockQuoteForm stockForm = createStockForm();
		webTestClient.post()
		.uri("/stock")
		.body(BodyInserters.fromValue(stockForm))
		.exchange()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectStatus().isCreated();
	}

	@Test
	void shouldReturn404ByCreatingAStockQuoteWithAInvalidStockId() {
		StockQuoteForm stockForm = createStockForm();
		stockForm.setStockId("mlgu4");
		webTestClient.post()
		.uri("/stock")
		.body(BodyInserters.fromValue(stockForm))
		.exchange()
		.expectStatus().isNotFound();
	}
	
	private StockQuoteForm createStockForm() {
		Map<LocalDate, Double> quoteMap = new HashMap<>();
		LocalDate date = LocalDate.now();
		quoteMap.put(date, 25.0);
		StockQuoteForm stockForm = new StockQuoteForm("petr4", quoteMap);
		return stockForm;
	}
}
