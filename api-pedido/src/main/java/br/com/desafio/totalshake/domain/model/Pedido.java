package br.com.desafio.totalshake.domain.model;

import br.com.desafio.totalshake.application.exception.ItemNaoExisteException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataHora;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "pedido", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("pedido")
    private List<ItemPedido> itens;

    @PrePersist
    void prePersist() {
        this.dataHora = LocalDateTime.now();
    }
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    private void nullSafetyofItensPedido() {
        if(itens == null){
            itens = new ArrayList<>();
        }
    }

    public void addItem(ItemPedido itemPedido){
        if(itemPedido != null){
            this.nullSafetyofItensPedido();
            itemPedido.setPedido(this);
            itens.add(itemPedido);
        }
    }

    public void addPedidoItens(long idPedido, int quantidade) throws ItemNaoExisteException {
        this.nullSafetyofItensPedido();
        this.itens.stream()
                .filter(itemPedido -> itemPedido.getId() == idPedido)
                .findFirst()
                .ifPresentOrElse(
                        itemPedido -> itemPedido.addItemQuantity(quantidade),
                        () -> { throw new ItemNaoExisteException(); }
                );
    }

    public void decreasePedidoItens(long idPedido, int quantidade) throws ItemNaoExisteException {
        this.nullSafetyofItensPedido();
        this.itens.stream()
                .filter(itemPedido -> itemPedido.getId() == idPedido)
                .findFirst()
                .ifPresentOrElse(
                        itemPedido -> {
                            int qtdAtual = itemPedido.decreaseItemQuantity(quantidade);
                            if(qtdAtual <= 0){
                                this.itens.remove(itemPedido);
                            }
                        },
                        () -> { throw new ItemNaoExisteException(); }
                );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Pedido pedido = (Pedido) object;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
