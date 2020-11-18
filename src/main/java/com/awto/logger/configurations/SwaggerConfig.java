package com.awto.logger.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(getApiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.awto.logger.webservices.controllers"))
				.paths(PathSelectors.any()).build().pathProvider(new RelativePathProvider(null) {
					@Override
					public String getApplicationBasePath() {
						return "/awto-logger";
					}
				});
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("Awto logger Test").description("Test for awto logger").version("0.0.1")
				.build();
	}
}
