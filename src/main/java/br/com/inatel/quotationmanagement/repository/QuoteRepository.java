package br.com.inatel.quotationmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.inatel.quotationmanagement.model.Quote;

/**
 * @author izaltino.
 * @since 10/07/2022
 */
@Repository
public interface QuoteRepository extends JpaRepository<Quote, String> {

	/**
	 * Delete quotes by stockId.
	 *
	 * @param stockId the stockId
	 */
	void deleteByStockId(String stockId);


}
