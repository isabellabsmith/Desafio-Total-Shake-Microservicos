package br.com.desafio.totalshake.application.exception;

public class PagamentoNaoExisteException extends RuntimeException {
    private static final String MSG = "Pedido não encontrado.";

    public PagamentoNaoExisteException() {
        super(MSG);
    }
}
