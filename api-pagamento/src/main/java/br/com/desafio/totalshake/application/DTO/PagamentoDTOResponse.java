package br.com.desafio.totalshake.application.DTO;

import br.com.desafio.totalshake.domain.model.FormaDePagamento;
import br.com.desafio.totalshake.domain.model.Pagamento;
import br.com.desafio.totalshake.domain.model.Status;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Check;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public class PagamentoDTOResponse {
    private BigDecimal valor;
    private String nome;
    private Status status;
    private Long pedidoId;
    private FormaDePagamento formaDePagamento;

    public PagamentoDTOResponse(Pagamento pagamento) {
        this.valor = pagamento.getValor();
        this.nome = pagamento.getNome();
        this.status = pagamento.getStatus();
        this.pedidoId = pagamento.getPedidoId();
        this.formaDePagamento = pagamento.getFormaDePagamento();
    }

    public PagamentoDTOResponse(){}
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
