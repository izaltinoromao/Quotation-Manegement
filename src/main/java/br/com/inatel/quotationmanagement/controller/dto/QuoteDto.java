package br.com.inatel.quotationmanagement.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.inatel.quotationmanagement.model.Quote;

/**
 * @author izaltino.
 * @since 10/07/2022
 */
public class QuoteDto {

	private String id;
	private LocalDate dataQuote;
	private double valorQuote;

	public QuoteDto(Quote quote) {
		this.id = quote.getId();
		this.dataQuote = quote.getDataQuote();
		this.valorQuote = quote.getValorQuote();
	}
	
	/**
	 * Convert quotes to quotesDto.
	 *
	 * @param quotes the quotes
	 * @return the list of quotesDto
	 */
	public static List<QuoteDto> convert(List<Quote> quotes) {
		return quotes.stream().map(QuoteDto::new).collect(Collectors.toList());
	}

	public String getId() {
		return id;
	}

	public LocalDate getDataQuote() {
		return dataQuote;
	}

	public double getValorQuote() {
		return valorQuote;
	}
}
