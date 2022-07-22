package br.com.inatel.quotationmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.inatel.quotationmanagement.model.Stock;

/**
 * @author izaltino.
 * @since 10/07/2022
 */
@Repository
public interface StockRepository extends JpaRepository<Stock, String> {

	/**
	 * Find a stock by stockId.
	 *
	 * @param stockId the stockId
	 * @return the list
	 */
	List<Stock> findByStockId(String stockId);

	/**
	 * Find one stock by stockId.
	 *
	 * @param stockId the stockId
	 * @return the stock
	 */
	Stock findOneByStockId(String stockId);

	/**
	 * Delete a stock by stockId.
	 *
	 * @param stockId the stockId
	 */
	void deleteByStockId(String stockId);


}
