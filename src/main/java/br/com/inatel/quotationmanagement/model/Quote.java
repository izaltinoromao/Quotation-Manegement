package br.com.inatel.quotationmanagement.model;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

/**
 * @author izaltino.
 * @since 10/07/2022
 */
@Entity
public class Quote {

	@Id
	private String id;
	@ManyToOne
	private Stock stock;
	private LocalDate dataQuote;
	private double valorQuote;

	public Quote() {}
	
	public Quote(LocalDate dataQuote, double valorQuote) {
		this.dataQuote = dataQuote;
		this.valorQuote = valorQuote;
	}

	public Quote(LocalDate dataQuote) {
		this.dataQuote = dataQuote;
	}

	public Quote(Stock stock, LocalDate dataQuote, double valorQuote) {
		this.stock = stock;
		this.dataQuote = dataQuote;
		this.valorQuote = valorQuote;
	}

	@PrePersist
	private void onSave() {
			this.id = UUID.randomUUID().toString();
	}

	public void setValorQuote(double valorQuote) {
		this.valorQuote = valorQuote;
	}

	public String getId() {
		return id;
	}

	public Stock getStock() {
		return stock;
	}

	public LocalDate getDataQuote() {
		return dataQuote;
	}

	public double getValorQuote() {
		return valorQuote;
	}
	
}
