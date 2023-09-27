package com.restaurant.cdstechchallenge;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig  {

    @Bean
    public OpenAPI apiInfo() {
        Contact contact = new Contact();
        contact.setEmail("rijil.daniel@blazeclan.com");
        contact.setName("Rijil Saji Daniel");
        return new OpenAPI().info(new Info()
                .title("Restaurant Random Picker")
                .version("1.0.0")
                .description("Restaurant Random Picker Appication API").contact(contact));
    }

    @Bean
    public GroupedOpenApi httpApi() {
        return GroupedOpenApi.builder()
                .group("http")
                .pathsToMatch("/**")
                .build();
    }

}
