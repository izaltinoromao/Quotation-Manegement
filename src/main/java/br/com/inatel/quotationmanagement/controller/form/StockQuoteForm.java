package br.com.inatel.quotationmanagement.controller.form;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.inatel.quotationmanagement.model.Quote;
import br.com.inatel.quotationmanagement.model.Stock;

// TODO: Auto-generated Javadoc
/**
 * The Class StockQuoteForm.
 */
public class StockQuoteForm {

	/** The stock id. */
	@NotNull @NotEmpty @Length(min = 5)
	private String stockId;
	
	/** The quotes map. */
	private Map<LocalDate, Double> quotesMap = new HashMap<LocalDate, Double>();

	/**
	 * Instantiates a new stock quote form.
	 *
	 * @param stockId the stock id
	 * @param quotesMap the quotes map
	 */
	public StockQuoteForm(@NotNull @NotEmpty @Length(min = 5) String stockId, Map<LocalDate, Double> quotesMap) {
		this.stockId = stockId;
		this.quotesMap = quotesMap;
	}
	
	/**
	 * Gets the stock id.
	 *
	 * @return the stock id
	 */
	public String getStockId() {
		return stockId;
	}
	
	/**
	 * Sets the stock id.
	 *
	 * @param stockId the new stock id
	 */
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	
	/**
	 * Gets the quotes map.
	 *
	 * @return the quotes map
	 */
	public Map<LocalDate, Double> getQuotesMap() {
		return quotesMap;
	}
	
	/**
	 * Sets the quotes map.
	 *
	 * @param quotesMap the quotes map
	 */
	public void setQuotesMap(Map<LocalDate, Double> quotesMap) {
		this.quotesMap = quotesMap;
	}
	
	/**
	 * Convert to stock.
	 *
	 * @return the stock
	 */
	public Stock convertToStock() {
		return new Stock(stockId);
	}

	/**
	 * Convert map of quotes to list of quotes.
	 *
	 * @param stock the stock
	 * @return the list
	 */
	public List<Quote> convertToQuotes(Stock stock) {
		List<Quote> quotes = new ArrayList<>();
		
		quotesMap.forEach((k,v) -> quotes.add(new Quote(stock, k, v)));
		
		addQuotesInStock(stock, quotes);
		
		return quotes;
	}
	
	/**
	 * Adds the quotes in stock.
	 *
	 * @param stock the stock
	 * @param quotes the quotes
	 */
	public void addQuotesInStock(Stock stock, List<Quote> quotes) {
		quotes.forEach(q -> stock.setQuotes(q));
		
	}
	
}
