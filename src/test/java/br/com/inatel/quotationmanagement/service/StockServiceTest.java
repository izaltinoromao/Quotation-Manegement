package br.com.inatel.quotationmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.inatel.quotationmanagement.model.Stock;

@SpringBootTest
@ActiveProfiles("test")
class StockServiceTest {
	
	@Autowired
	private StockService stockService;
	
	@Test
	void givenAnValidStockIdShouldReturnAExistentStock() {

		String validStockId = "test4";
		
		Stock stockFound = stockService.findOneStockWithQuotesByStockId(validStockId);
		
		assertEquals(validStockId, stockFound.getStockId());
	}
	
	@Test
	void givenAnInvalidStockIdShouldReturnANullStock() {
		
		String validStockId = "petr5";
		
		Stock stockFound = stockService.findOneStockWithQuotesByStockId(validStockId);
		
		assertEquals(null, stockFound);
	}

	@Test
	void ShouldReturnAListOfStocks() {
		
		List<Stock> stockList = stockService.findAllWithQuotes();
		assertFalse(stockList.isEmpty());
	}
	
	@Test
	void givenAnValidStockShouldSaveAtDb() {
		
		Stock toSave = new Stock("petr3");
		
		stockService.saveDbStock(toSave);
		
		Stock stockFound = stockService.findOneStockWithQuotesByStockId(toSave.getStockId());
		
		assertNotEquals(null, stockFound);
	}
	
	@Test
	void givenAnValidStockIdShouldDeleteFromDb() {
		
		String stockId = "test5";
		
		Stock stockToDelete = stockService.findOneStockWithQuotesByStockId(stockId);
		
		stockService.deleteQuotesFromStock(stockToDelete);
        stockService.deleteByStockId(stockId);
		
		Stock stockFound = stockService.findOneStockWithQuotesByStockId(stockId);
		
		assertEquals(null, stockFound);
	}
}
