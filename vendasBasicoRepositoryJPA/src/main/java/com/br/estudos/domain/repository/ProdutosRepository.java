package com.br.estudos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.estudos.domain.entity.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Integer>{

}
