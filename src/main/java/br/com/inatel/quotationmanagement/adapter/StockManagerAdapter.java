package br.com.inatel.quotationmanagement.adapter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.inatel.quotationmanagement.adapter.dto.StockManagerDto;
import br.com.inatel.quotationmanagement.adapter.form.NotificationForm;
import reactor.core.publisher.Flux;

/**
 * @author izaltino.
 * @since 15/07/2022
 */
@Service
public class StockManagerAdapter {
		
		@Value("${connect.endpoint}")
		private String URL_MANAGER;
		@Value("${connect.host}")
		private String host;
		@Value("${connect.port}")
		private int port;
		
		/**
		 * List all stocks from de external api StockManager.
		 *
		 * @return the list of stocks at stock-manager
		 */
		@Cacheable(value = "stocksAtManagerList")
		public List<StockManagerDto> listAllStocks() {
			
			List<StockManagerDto> stocksAtManager = new ArrayList<>();
			
			Flux<StockManagerDto> fluxDto = WebClient.create(URL_MANAGER)
					.get()
					.uri("/stock")
					.retrieve()
					.bodyToFlux(StockManagerDto.class);
			
			fluxDto.subscribe(f -> stocksAtManager.add(f));
		    fluxDto.blockLast();
			
		    return stocksAtManager;
		} 
		
		/**
		 * Do the register of the quotation-manegement API inside the stock-manager API.
		 * PostConstrucct annotation to to this at the startup
		 */
		@PostConstruct
		public void notificationRegister () {

			NotificationForm nf = new NotificationForm(host,port);
			
			WebClient.create(URL_MANAGER)
			.post()
			.uri("/notification")
			.bodyValue(nf)
			.retrieve()
			.toBodilessEntity()
			.block();
			
		}

	
}
