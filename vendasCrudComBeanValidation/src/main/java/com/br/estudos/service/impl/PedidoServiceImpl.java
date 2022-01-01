package com.br.estudos.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.estudos.domain.entity.Cliente;
import com.br.estudos.domain.entity.ItemPedido;
import com.br.estudos.domain.entity.Pedido;
import com.br.estudos.domain.entity.Produto;
import com.br.estudos.domain.enuns.StatusPedido;
import com.br.estudos.domain.repository.ClientesRepository;
import com.br.estudos.domain.repository.ItemsPedidoRepository;
import com.br.estudos.domain.repository.PedidosRepository;
import com.br.estudos.domain.repository.ProdutosRepository;
import com.br.estudos.exception.PedidoNaoEncontradoException;
import com.br.estudos.exception.RegraNegocioException;
import com.br.estudos.rest.dto.ItemPedidoDTO;
import com.br.estudos.rest.dto.PedidoDTO;
import com.br.estudos.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidosRepository pedidosRepository;
	@Autowired
	private ClientesRepository clientesRepository;
	@Autowired
	private ProdutosRepository produtosRepository;
	@Autowired
	private ItemsPedidoRepository itemsPedidoRepository;

	@Override
	@Transactional
	public Pedido salvar(PedidoDTO dto) {
		Integer idCliente = dto.getCliente();
		Cliente cliente = clientesRepository.findById(idCliente)
				.orElseThrow(() -> new RegraNegocioException("Código de cliente inválido"));
		Pedido pedido = new Pedido();
		pedido.setTotal(dto.getTotal());
		pedido.setDataPedido(LocalDate.now());
		pedido.setCliente(cliente);
		pedido.setStatus(StatusPedido.REALIZADO);

		List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
		pedidosRepository.save(pedido);
		itemsPedidoRepository.saveAll(itemsPedido);
		pedido.setItens(itemsPedido);
		return pedido;
	};

	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
		if (items.isEmpty()) {
			new RegraNegocioException("Não é possível realizar um pedido sem items");
		}

		return items.stream().map(dto -> {
			Integer idProduto = dto.getProduto();
			Produto produto = produtosRepository.findById(idProduto)
					.orElseThrow(() -> new RegraNegocioException("Código produto invalido: " + idProduto));
			ItemPedido itemPedido = new ItemPedido();
			itemPedido.setQuantidade(dto.getQuantidade());
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			return itemPedido;
		}).collect(Collectors.toList());

	}

	@Override
	public Optional<Pedido> obterPedidoCompleto(Integer id) {
		return pedidosRepository.findByIdFetchItens(id);
	}

	@Override
	@Transactional
	public void atualizaStatus(Integer id, StatusPedido statusPedido) {

		pedidosRepository.findById(id).map(pedido -> {
			pedido.setStatus(statusPedido);
			return pedidosRepository.save(pedido);
		}).orElseThrow(() -> new PedidoNaoEncontradoException());
	};

}
