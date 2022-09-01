package br.com.desafio.totalshake.application.exception;

public class QuantidadeInvalidaException extends RuntimeException {
    private static final String MSG = "Quantidade inválida de item. Deve ser maior que 0.";

    public QuantidadeInvalidaException() {
        super(MSG);
    }
}
