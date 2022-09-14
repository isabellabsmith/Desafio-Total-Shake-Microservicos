package br.com.desafio.totalshake.domain.service;

import br.com.desafio.totalshake.application.request.PedidoDTOPost;
import br.com.desafio.totalshake.application.response.PedidoDTOResponse;
import br.com.desafio.totalshake.application.exception.PedidoNaoExisteException;
import br.com.desafio.totalshake.domain.model.Pedido;
import br.com.desafio.totalshake.domain.model.Status;
import br.com.desafio.totalshake.domain.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public Pedido savePedido(PedidoDTOPost pedidoDTOPost) {

        var pedido = pedidoDTOPost.toPedidoModel();

        pedido.setStatus(Status.REALIZADO);
        pedido.setDataHora(LocalDateTime.now());
        pedido = pedidoRepository.save(pedido);

        return pedido;
    }

    @Transactional
    public PedidoDTOResponse addItem(Long pedidoId, Long itemId, int quantidade){
        var pedido = this.findPedidoById(pedidoId);
        pedido.addPedidoItens(itemId, quantidade);
        pedido = pedidoRepository.save(pedido);

        return new PedidoDTOResponse(pedido);
    }

    @Transactional
    public PedidoDTOResponse decreaseItemQuantity(Long pedidoId, Long itemId, int quantidade) {
        var pedido = this.findPedidoById(pedidoId);
        pedido.decreasePedidoItens(itemId, quantidade);
        pedido = pedidoRepository.save(pedido);

        return new PedidoDTOResponse(pedido);
    }

    @Transactional
    public PedidoDTOResponse cancelPedido(Long idPedido) {
        var pedido = this.findPedidoById(idPedido);
        pedido.setStatus(Status.CANCELADO);
        pedido = pedidoRepository.save(pedido);

        return new PedidoDTOResponse(pedido);
    }

    @Transactional
    public PedidoDTOResponse pedidoPago(Long idPedido) {
        var pedido = this.findPedidoById(idPedido);
        pedido.setStatus(Status.PAGO);
        pedido = pedidoRepository.save(pedido);

        return new PedidoDTOResponse(pedido);
    }

    @Transactional
    public Pedido findPedidoById(long id) throws PedidoNaoExisteException {
        return pedidoRepository
                .findById(id)
                .orElseThrow(PedidoNaoExisteException::new);
    }

    @Transactional
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Transactional
    public void deleteById(long id) {
        findPedidoById(id);
        pedidoRepository.delete(findPedidoById(id));
    }
}
