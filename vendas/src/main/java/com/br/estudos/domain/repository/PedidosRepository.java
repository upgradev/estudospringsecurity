package com.br.estudos.domain.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.estudos.domain.entity.Cliente;
import com.br.estudos.domain.entity.Pedido;

public interface PedidosRepository extends JpaRepository<Pedido, Integer>{
	
	Set<Pedido> findByCliente(Cliente cliente);
	
}
