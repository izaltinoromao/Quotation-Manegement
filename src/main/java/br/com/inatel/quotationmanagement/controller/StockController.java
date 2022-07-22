package br.com.inatel.quotationmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.inatel.quotationmanagement.controller.dto.StockQuoteDto;
import br.com.inatel.quotationmanagement.controller.form.StockQuoteForm;
import br.com.inatel.quotationmanagement.model.Quote;
import br.com.inatel.quotationmanagement.model.Stock;
import br.com.inatel.quotationmanagement.service.StockService;

/**
 * @author izaltino.
 * @since 10/07/2022
 */
@RestController
public class StockController {

	@Autowired
	private StockService stockService;
	
	/**
	 * Do get.
	 *
	 * @return the list of all stocks
	 */
	@GetMapping("/stock")
	@Cacheable(value = "stocksList")
	public List<StockQuoteDto> list() {
		
			List<Stock> stocks = stockService.findAllWithQuotes();
			List<StockQuoteDto> StocksQuotesDto = StockQuoteDto.convert(stocks);
			return StocksQuotesDto;
	}
	
	/**
	 * Do get.
	 *
	 * @param stockId the stock id
	 * @return only one stock found by stockId
	 */
	@GetMapping("/stock/{stockId}")
	@Cacheable(value = "oneStock")
	public ResponseEntity<StockQuoteDto> listOne(@PathVariable(required = true) String stockId) {
			Stock stock = stockService.findOneStockWithQuotesByStockId(stockId);
			if(stock != null) {
				StockQuoteDto stockQuoteDto= StockQuoteDto.convertOneStock(stock);
				return ResponseEntity.ok(stockQuoteDto);
			}
			return ResponseEntity.notFound().build();

	}
	
	/**
	 * Do post.
	 *
	 * @param form the form
	 * @return Response entity + body
	 */
	@PostMapping("/stock")
	@CacheEvict(value = {"stocksList", "oneStock"}, allEntries = true)
	public ResponseEntity<StockQuoteDto> post(@RequestBody @Valid StockQuoteForm form) {
		
		Stock validationStock = stockService.findOneStockWithQuotesByStockId(form.getStockId());
		Stock stock = form.convertToStock();
		
		if(validationStock != null) {
			List<Quote> quotes = form.convertToQuotes(validationStock);
			stockService.saveDbQuote(quotes);
			ResponseEntity<StockQuoteDto> re = new ResponseEntity<>( StockQuoteDto.convertOneStock(validationStock), HttpStatus.CREATED);
			return re;
		} else if (stockService.existsAtStockManager(stock)) {
			stock = stockService.saveDbStock(stock);
			List<Quote> quotes = form.convertToQuotes(stock);
			stockService.saveDbQuote(quotes);
			ResponseEntity<StockQuoteDto> re = new ResponseEntity<>( StockQuoteDto.convertOneStock(stock), HttpStatus.CREATED);
			return re;
		}
		
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * Remove form stock quote.
	 *
	 * @param stockId the stock id
	 * @return Response entity
	 */
	@DeleteMapping("/stock/{stockId}")
	@CacheEvict(value = {"stocksList", "oneStock"}, allEntries = true)
	public ResponseEntity<?> remove(@PathVariable String stockId) {
		Stock stock = stockService.findOneStockWithQuotesByStockId(stockId);
        if (stock != null) {
        	stockService.deleteQuotesFromStock(stock);
            stockService.deleteByStockId(stockId);
                return ResponseEntity.ok().build();
      }
        return ResponseEntity.notFound().build();
	}
	
	/**
	 * Clean cache.
	 */
	@DeleteMapping("/stockcache")
	@CacheEvict(value = "stocksAtManagerList")
	public void cleanCache() {
		System.out.println("The cache was cleaned");
	}
	

}
