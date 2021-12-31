package com.br.estudos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//@Configuration
//@Profile("development")
@Development
public class MinhaConfiguration {
  
	
	@Bean
	public CommandLineRunner executar() {
		return args -> {
			System.out.println("Rodando a configuração de desenvolvimento");
		};
	}
    
    
}