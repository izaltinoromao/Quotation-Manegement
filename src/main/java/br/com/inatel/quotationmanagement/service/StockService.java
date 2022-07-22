package br.com.inatel.quotationmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.inatel.quotationmanagement.adapter.StockManagerAdapter;
import br.com.inatel.quotationmanagement.adapter.dto.StockManagerDto;
import br.com.inatel.quotationmanagement.model.Quote;
import br.com.inatel.quotationmanagement.model.Stock;
import br.com.inatel.quotationmanagement.repository.QuoteRepository;
import br.com.inatel.quotationmanagement.repository.StockRepository;

/**
 * @author izaltino.
 * @since 10/07/2022
 */
@Service
@Transactional
public class StockService {

	private StockRepository stockRepository;
	private QuoteRepository quoteRepository;
	private StockManagerAdapter stockManagerAdapter;

	@Autowired
	public StockService(StockRepository stockRepository, QuoteRepository quoteRepository,
			StockManagerAdapter stockManagerAdapter) {
		this.stockRepository = stockRepository;
		this.quoteRepository = quoteRepository;
		this.stockManagerAdapter = stockManagerAdapter;
	}

	/**
	 * Find all stocks with quotes.
	 *
	 * @return the list of stocks
	 */
	public List<Stock> findAllWithQuotes(){
		
		List<Stock> stocks = stockRepository.findAll();
		stocks.forEach(s -> s.getQuotes().size());
		return stocks;
	}
	
	/**
	 * Find one stock by stockId.
	 *
	 * @param stockId the stockId
	 * @return the stock
	 */
	public Stock findOneStockWithQuotesByStockId(String stockId) {
		
		Stock stock = stockRepository.findOneByStockId(stockId);
		if(stock != null) {
			stock.getQuotes().size();
			return stock;
		}
		
		return null;
	}

	/**
	 * Save stock at DB.
	 *
	 * @param stock the stock
	 * @return the stock
	 */
	public Stock saveDbStock(Stock stock) {
		stock = stockRepository.save(stock);
		return stock;
	}
	
	/**
	 * Save quotes at DB.
	 *
	 * @param quotes the quotes
	 */
	public void saveDbQuote(List<Quote> quotes) {
		
		quotes.forEach(q -> quoteRepository.save(q));
	
	}

	/**
	 * Delete stock by stockId.
	 *
	 * @param stockId the stockId
	 */
	public void deleteByStockId(String stockId) {
		stockRepository.deleteByStockId(stockId);
		
	}

	/**
	 * Delete quotes from stock.
	 *
	 * @param stock the stock
	 */
	public void deleteQuotesFromStock(Stock stock) {
		quoteRepository.deleteByStockId(stock.getId());
	}

	/**
	 * verify if exists at stock manager API.
	 *
	 * @param stock the stock
	 * @return true, if successful
	 */
	public boolean existsAtStockManager(Stock stock) {
		
		
		List<StockManagerDto> stocksAtManager = stockManagerAdapter.listAllStocks();

		return stocksAtManager.stream().anyMatch(i -> i.getId().equals(stock.getStockId()));
	}
	
}
