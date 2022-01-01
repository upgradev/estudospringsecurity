package com.br.estudos.rest.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.br.estudos.domain.entity.Cliente;
import com.br.estudos.domain.entity.Pedido;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDTO {

	private Integer cliente;
	private BigDecimal total;
	private List<ItemPedidoDTO> items;



}
