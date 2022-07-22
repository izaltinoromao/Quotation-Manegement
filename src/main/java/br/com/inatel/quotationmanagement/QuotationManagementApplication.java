package br.com.inatel.quotationmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author izaltino.
 * @since 10/07/2022
 */
@SpringBootApplication
@EnableCaching
@EnableSwagger2
public class QuotationManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotationManagementApplication.class, args);
	}

}
