package br.com.desafio.totalshake.application.DTO;

import br.com.desafio.totalshake.domain.model.FormaDePagamento;
import br.com.desafio.totalshake.domain.model.Pagamento;
import org.hibernate.annotations.Check;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class PagamentoDTOPost {

    @Check(constraints = "valor > 0")
    private BigDecimal valor;

    @NotBlank
    @Size(min = 1, max = 100)
    private String nome;

    @NotBlank
    @Size(min = 1, max = 100)
    private String numero;

    private String expiracao;

    @NotBlank
    @Size(min = 3, max = 3)
    private String codigo;

    private Long pedidoId;

    private FormaDePagamento formaDePagamento;

    PagamentoDTOPost() {}

    public Pagamento toPagamento() {
        return new Pagamento(this.getValor(), this.getNome(), this.getNumero(), this.getExpiracao(), this.getCodigo(),
                this.getPedidoId(), this.getFormaDePagamento());
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getExpiracao() {
        return expiracao;
    }

    public void setExpiracao(String expiracao) {
        this.expiracao = expiracao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }
}
