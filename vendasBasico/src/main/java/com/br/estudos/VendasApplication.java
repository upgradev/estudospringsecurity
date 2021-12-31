package com.br.estudos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
// quais classes de configuracao que tem q escanear, array de pacotes,
// usar quando tiver um pacote fora do pacote principal no caso fora do com.br.estudos
// @ComponentScan(basePackages = {"com.br.estudos.repository, com.br.estudos.service"})
@RestController
public class VendasApplication {

	@Value("${application.name}")
	private String applicationName;

//	@Autowired
//	@Qualifier("gato")
//	annotation customizada
	@Gato
	private Animal animal;

	@Bean(name = "executarAnimal")
	public CommandLineRunner executar() {
		return args -> {
			this.animal.fazerBarulho();
		};
	}

	@GetMapping("/hello")
	public String helloWorld() {
		return applicationName;
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
