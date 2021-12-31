package com.br.estudos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.br.estudos.domain.entity.Cliente;
import com.br.estudos.domain.entity.Pedido;
import com.br.estudos.domain.repository.ClientesRepository;
import com.br.estudos.domain.repository.PedidosRepository;

@SpringBootApplication
public class VendasApplication {

	@Bean
	public CommandLineRunner init(@Autowired ClientesRepository clientes, @Autowired PedidosRepository pedidos) {
		return args -> {
			System.out.println("Salvando clientes");
			Cliente fulano = new Cliente("ana");
			clientes.save(fulano);
//			clientes.save(new Cliente("outro cliente"));
			
			Pedido p = new Pedido();
			p.setCliente(fulano);
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100));
			pedidos.save(p);
			
//			Cliente cliente = clientes.findClienteFetchPedidos(fulano.getId());
//			System.out.println(cliente);
//			System.out.println(cliente.getPedidos());
			
			pedidos.findByCliente(fulano).forEach(System.out::println);
			
			
//			List<Cliente> todosCliente = clientes.findAll();
//			todosCliente.forEach(System.out::println);

//			System.out.println("Atualizando clientes");
//			todosCliente.forEach(c -> {
//				c.setNome(c.getNome() + " atualizado");
//				clientes.save(c);
//			});
//
//			todosCliente = clientes.findAll();
//			todosCliente.forEach(System.out::println);
//
//			System.out.println("Buscando clientes");
//			clientes.findByNomeLike("cli").forEach(System.out::println);
//
//			System.out.println("Deletando clientes");
//			clientes.findAll().forEach(c -> {
//				clientes.delete(c);
//			});
//			todosCliente = clientes.findAll();
//			if (todosCliente.isEmpty()) {
//				System.out.println("nenhum cliente encontrado");
//			} else {
//				todosCliente.forEach(System.out::println);
//			}

		};

	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
