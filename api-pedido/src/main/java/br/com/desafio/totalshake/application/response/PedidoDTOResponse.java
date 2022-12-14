package br.com.desafio.totalshake.application.response;

import br.com.desafio.totalshake.domain.model.ItemPedido;
import br.com.desafio.totalshake.domain.model.Pedido;
import br.com.desafio.totalshake.domain.model.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoDTOResponse {
    private Long id;
    private Status status;

    @JsonIgnoreProperties("pedido")
    private List<ItemPedido> itens;

    public PedidoDTOResponse(Pedido pedidoCriado) {
        this.id = pedidoCriado.getId();
        this.itens = pedidoCriado.getItens();
        this.status = pedidoCriado.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
