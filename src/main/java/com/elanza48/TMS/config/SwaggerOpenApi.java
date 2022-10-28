package com.elanza48.TMS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Component
public class SwaggerOpenApi {

  @Bean
  public OpenAPI velocityToursOpenAPI() {
      return new OpenAPI().info(new Info()
              .title("Velocity Tours API")
              .description("Velocity Tours REST API endpoints exposed")
              .version("0.8.5-ALPHA")
              .license(new License().name("MIT").url("https://choosealicense.com/licenses/mit/"))
              .contact(new Contact().name("elanza48").email("elanza48@outlook.com")));
  }
}
