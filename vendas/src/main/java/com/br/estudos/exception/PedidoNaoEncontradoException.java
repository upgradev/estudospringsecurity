package com.br.estudos.exception;

public class PedidoNaoEncontradoException extends RuntimeException {
	public PedidoNaoEncontradoException() {
		super("Pedido não encontrado");
	}
}
