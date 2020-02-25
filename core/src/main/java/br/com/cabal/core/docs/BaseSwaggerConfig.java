package br.com.cabal.core.docs;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;
public class BaseSwaggerConfig {

	private final String basePackage;

	public BaseSwaggerConfig(String basePackage) {
		this.basePackage = basePackage;
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title("Cadastro de estabelecimentos comerciais")
				.description("Sistema de cadastro para estabelecimentos comerciais")
				.version("1.0")
				.contact(new Contact("David Rezende","http://cabal.com.br","david.rezende@cabal.com.br" ))
				.license("None")
				.license("http://cabal.com.br")
				.build();
	}
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(basePackage))
				.build()
				.apiInfo(metaData());
	}
}
