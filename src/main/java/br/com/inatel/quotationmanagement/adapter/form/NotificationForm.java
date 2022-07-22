package br.com.inatel.quotationmanagement.adapter.form;

/**
 * @author izaltino.
 * @since 15/07/2022
 */
public class NotificationForm {
	
	private String host;
	
	private int port;

	public NotificationForm(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}
	
	
	
}
