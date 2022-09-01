package br.com.desafio.totalshake.domain.model;

import br.com.desafio.totalshake.application.exception.QuantidadeInvalidaException;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="item_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidade;
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    public ItemPedido(String descricao, int quantidade) {
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public ItemPedido(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int decreaseItemQuantity(int quantidadeParaReduzir) {
        this.validateQuantity(quantidadeParaReduzir);
        return this.quantidade - quantidadeParaReduzir;
    }

    public int addItemQuantity(int quantidadeParaAcrescentar) {
        this.validateQuantity(quantidadeParaAcrescentar);
        return this.quantidade + quantidadeParaAcrescentar;
    }

    private void validateQuantity(int quantity) throws QuantidadeInvalidaException {
        if(quantity <= 0){
            throw new QuantidadeInvalidaException();
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        ItemPedido that = (ItemPedido) object;
        return Objects.equals(id, that.id) && Objects.equals(descricao, that.descricao) && Objects.equals(pedido, that.pedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, pedido);
    }
}
