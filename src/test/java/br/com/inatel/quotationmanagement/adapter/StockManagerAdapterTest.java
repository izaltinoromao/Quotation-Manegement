package br.com.inatel.quotationmanagement.adapter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import br.com.inatel.quotationmanagement.adapter.form.NotificationForm;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class StockManagerAdapterTest {
	
	@Value("${connect.endpoint}")
	private String stockManagerURL;
	
	@Autowired
	private WebTestClient webTestClient;


	@Test
	void shouldReturnAListOfStocks() {
		webTestClient.get()
		.uri(stockManagerURL + "/stock")
		.exchange()
		.expectStatus().isOk();
		
	}
	
	@Test
	void shouldRegisterTheApiAtStockManager() {
		
		String host = "testhost";
		int port = 9595;
		NotificationForm nf = new NotificationForm(host, port);
		
		webTestClient.post()
		.uri( stockManagerURL + "/notification")
		.body(BodyInserters.fromValue(nf))
		.exchange()
		.expectStatus().isOk()
		.expectBody().returnResult();
		
		
	}

}
