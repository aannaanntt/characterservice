package com.example.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	
	@Value("${rpg.openapi.dev-url}")
	private String devUrl;
	
	@Bean
	public OpenAPI myOpenApi() {
		io.swagger.v3.oas.models.servers.Server serv= new io.swagger.v3.oas.models.servers.Server();
		serv.setUrl(devUrl);
		serv.setDescription("This is development URL");
		
		  io.swagger.v3.oas.models.info.Contact contact = new io.swagger.v3.oas.models.info.Contact();
		    contact.setEmail("anantmishrs2307@gmail.com");
		    contact.setName("Anant");
		    contact.setUrl("https://www.anant.com");
		    
		    Info info = new Info()
		            .title("Character Management API")
		            .version("1.0")
		            .contact(contact)
		            .description("This API exposes endpoints to manage Characters.").termsOfService("https://www.anant.com/terms");

		    
		    return new OpenAPI()
	                  .addSecurityItem(new SecurityRequirement()
	                		  .addList("bearerAuth")).components(new Components()
	                		                                    .addSecuritySchemes(
	                		                                          "bearerAuth", new SecurityScheme()
	                		                                          .name("bearerAuth")
	                		                                          .type(SecurityScheme.Type.HTTP)
	                		                                          .bearerFormat("JWT")
	                		                                          .in(SecurityScheme.In.HEADER)
	                		                                          .scheme("bearer")
	                		                                    )
	                		                    )
		    		
		    		
		    		
		    		
		    		
		    		
		    		.info(info).servers(List.of(serv));
		    		
		
	}

}
