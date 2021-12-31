package com.br.estudos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.estudos.domain.entity.ItemPedido;

public interface ItemsPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
