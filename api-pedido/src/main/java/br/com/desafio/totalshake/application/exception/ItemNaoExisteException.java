package br.com.desafio.totalshake.application.exception;

public class ItemNaoExisteException extends RuntimeException {
    private static final String MSG = "Item não existe no pedido.";

    public ItemNaoExisteException() {
        super(MSG);
    }
}
