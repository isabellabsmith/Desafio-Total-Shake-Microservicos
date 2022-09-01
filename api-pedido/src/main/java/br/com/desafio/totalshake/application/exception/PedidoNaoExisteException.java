package br.com.desafio.totalshake.application.exception;

public class PedidoNaoExisteException extends RuntimeException{
    private static final String MSG = "Pedido não existe.";

    public PedidoNaoExisteException() {
        super(MSG);
    }
}
