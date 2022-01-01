package com.br.estudos.service;

import java.util.Optional;

import com.br.estudos.domain.entity.Pedido;
import com.br.estudos.domain.enuns.StatusPedido;
import com.br.estudos.rest.dto.PedidoDTO;

public interface PedidoService  {
	
	Pedido salvar(PedidoDTO dto);
	
	Optional<Pedido> obterPedidoCompleto(Integer id);
	
	void atualizaStatus(Integer id, StatusPedido statusPedido);

}
