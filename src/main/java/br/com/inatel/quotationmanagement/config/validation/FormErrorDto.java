package br.com.inatel.quotationmanagement.config.validation;

/**
 * @author izaltino.
 * @since 14/07/2022
 */
public class FormErrorDto {

	private String campo;
	private String erro;

	public FormErrorDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
	
	
}
