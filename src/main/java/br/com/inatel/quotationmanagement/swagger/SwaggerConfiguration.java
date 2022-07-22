package br.com.inatel.quotationmanagement.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author izaltino.
 * @since 10/07/2022
 */
@Configuration
public class SwaggerConfiguration {

	/**
	 * Configuring the web page of swagger documentation.
	 *
	 * @return the docket
	 */
	@Bean	
	public Docket forumApi() {
		return new Docket(DocumentationType.SWAGGER_2)
		        .select()
		        .apis(RequestHandlerSelectors.basePackage("br.com.inatel.quotationmanagement"))
		        .paths(PathSelectors.ant("/**"))
		        .build();
	}
}
