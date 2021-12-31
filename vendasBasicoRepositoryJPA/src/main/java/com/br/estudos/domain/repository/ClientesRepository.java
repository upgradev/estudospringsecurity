package com.br.estudos.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.estudos.domain.entity.Cliente;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

//	@Query(value = "select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true)
	@Query(value = "select c from Cliente c where c.nome like :nome")
	List<Cliente> encontrarPorNome(@Param("nome") String nome);

	@Query("delete from Cliente c where c.nome =:nome ")
	@Modifying
	void deleteByNome(String nome);

	boolean existsByNome(String nome);

//	clientes com os pedidos
	@Query(" select c from Cliente c left join fetch c.pedidos p where c.id = :id ")
	Cliente findClienteFetchPedidos(@Param("id") Integer id);
	
	

}
