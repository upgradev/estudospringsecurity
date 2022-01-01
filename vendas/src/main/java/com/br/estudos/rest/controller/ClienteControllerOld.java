package com.br.estudos.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.estudos.domain.entity.Cliente;
import com.br.estudos.domain.repository.ClientesRepository;

//@Controller
//@RequestMapping("/api/clientes")
public class ClienteControllerOld {

	@Autowired
	private ClientesRepository clientesRepository;

	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer id) {
		Optional<Cliente> cliente = clientesRepository.findById(id);
		if (cliente.isPresent()) {
//			ResponseEntity<Cliente> responseEntity = new ResponseEntity<>(cliente.get(), HttpStatus.OK);
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity save(@RequestBody Cliente cliente) {
		Cliente clienteSalvo = clientesRepository.save(cliente);
		return ResponseEntity.ok(clienteSalvo);

	}

	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity delete(@PathVariable("id") Integer id) {
		Optional<Cliente> cliente = clientesRepository.findById(id);
		if (cliente.isPresent()) {
			clientesRepository.delete(cliente.get());
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity atualizarCliente(@PathVariable("id") Integer id, @RequestBody Cliente cliente) {
		return clientesRepository.findById(id).map(clienteExistente -> {
			cliente.setId(clienteExistente.getId());
			clientesRepository.save(cliente);
			return ResponseEntity.noContent().build();
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping
	public ResponseEntity find(Cliente filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		Example example = Example.of(filtro, matcher);
		List<Cliente> clientes = clientesRepository.findAll(example);
		return ResponseEntity.ok(clientes);
	}

}
