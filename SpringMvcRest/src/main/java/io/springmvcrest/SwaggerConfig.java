package io.springmvcrest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apInfo());
	}

	private ApiInfo apInfo() {

		ApiInfo info = new ApiInfo("Sprng-REST", "A practice for fun project using springMVC", "1.0.0", "T&C",
				new Contact("kauhsik nandhan", "kaushiknandhan@gmail.com", "kaushiknandhan@gmail.com"), "MIT",
				"https://opensource.org/licenses/MIT");
		return info;
	}

}
