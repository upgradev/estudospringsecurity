package com.br.estudos.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.br.estudos.domain.entity.Cliente;
import com.br.estudos.domain.entity.Produto;
import com.br.estudos.domain.repository.ProdutosRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutosRepository repository;

	@PostMapping
	@ResponseStatus(CREATED)
	public Produto save(@RequestBody @Valid Produto produto) {
		return repository.save(produto);
	}

	@PutMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void update(@RequestBody @Valid Produto produto, @PathVariable("id") Integer id) {
		repository.findById(id).map(p -> {
			produto.setId(p.getId());
			repository.save(produto);
			return produto;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(NO_CONTENT)
	public void delete(@PathVariable("id") Integer id) {
		repository.findById(id).map(p -> {
			repository.deleteById(id);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}

	@GetMapping("/{id}")
	public Produto getById(@PathVariable("id") Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
	}

	@GetMapping
	public List<Produto> find(Produto filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		Example example = Example.of(filtro, matcher);
		List<Cliente> clientes = repository.findAll(example);
		return repository.findAll(example);
	}

}
