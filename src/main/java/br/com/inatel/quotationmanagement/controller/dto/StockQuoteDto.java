package br.com.inatel.quotationmanagement.controller.dto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.inatel.quotationmanagement.model.Quote;
import br.com.inatel.quotationmanagement.model.Stock;

/**
 * @author izaltino.
 * @since 10/07/2022
 */
public class StockQuoteDto {

	private String id;
	private String stockId;
	private Map<LocalDate, Double> quotesMap;

	public StockQuoteDto(Stock stock) {
		this.id = stock.getId();
		this.stockId = stock.getStockId();
		quotesMap = new HashMap<>();
		buildingMap(stock.getQuotes());
	}
	
	/**
	 * Building at instance map from the list of quotes from stock.
	 */
	private void buildingMap (List<Quote> quotes) {
		
		quotes.forEach(t -> quotesMap.put(t.getDataQuote(), t.getValorQuote()));
		
	}
	
	public String getId() {
		return id;
	}

	public String getStockId() {
		return stockId;
	}

	public Map<LocalDate, Double> getQuotesMap() {
		return quotesMap;
	}

	/**
	 * Convert stocks to stockQuotesDto.
	 *
	 * @param stocks the stocks
	 * @return the list of stockQuotesDto
	 */
	public static List<StockQuoteDto> convert(List<Stock> stocks) {
		return stocks.stream().map(StockQuoteDto::new).collect(Collectors.toList());
	}

	/**
	 * Convert one stock to stockQuoteDto.
	 *
	 * @param stock the stock
	 * @return the stockQuoteDto
	 */
	public static StockQuoteDto convertOneStock(Stock stock) {
		return new StockQuoteDto(stock);
	}
	

}
