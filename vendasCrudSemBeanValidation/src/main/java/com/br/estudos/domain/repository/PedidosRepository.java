package com.br.estudos.domain.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.estudos.domain.entity.Cliente;
import com.br.estudos.domain.entity.Pedido;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {

	Set<Pedido> findByCliente(Cliente cliente);

	@Query(" select p from Pedido p join fetch p.itens where p.id = :id ")
	Optional<Pedido> findByIdFetchItens(@Param("id")  Integer id);

}
