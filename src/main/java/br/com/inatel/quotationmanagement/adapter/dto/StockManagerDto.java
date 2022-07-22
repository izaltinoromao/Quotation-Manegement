package br.com.inatel.quotationmanagement.adapter.dto;

/**
 * @author izaltino.
 * @since 15/07/2022
 */
public class StockManagerDto {

	private String id;
	
	private String description;

	public StockManagerDto() {}

	public StockManagerDto(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
}
