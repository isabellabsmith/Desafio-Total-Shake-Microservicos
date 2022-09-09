package br.com.desafio.totalshake.application.controller;

import br.com.desafio.totalshake.application.request.PedidoDTOPost;
import br.com.desafio.totalshake.application.response.PedidoDTOResponse;
import br.com.desafio.totalshake.domain.model.Pedido;
import br.com.desafio.totalshake.domain.repository.PedidoRepository;
import br.com.desafio.totalshake.domain.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/pedido")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody @Valid PedidoDTOPost pedidoPostDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoService.savePedido(pedidoPostDTO));
    }

    @GetMapping
    public List<Pedido> findAll(){
        return pedidoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTOResponse> buscarPedido(@PathVariable Long id){
        return ResponseEntity.ok(new PedidoDTOResponse(pedidoService.findPedidoById(id)));
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<PedidoDTOResponse> cancelarPedido(@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.cancelPedido(id));
    }

    @PutMapping("/pago/{id}")
    public ResponseEntity<PedidoDTOResponse> mudarStatusPedidoPago(@PathVariable Long id){
        return ResponseEntity.ok(pedidoService.pedidoPago(id));
    }

    @PutMapping("/{id}/itens/{itemId}/acrescentar/{quantidade}")
    public ResponseEntity<PedidoDTOResponse> acrescentarItem(@PathVariable Long id, @PathVariable Long itemId,
                                                             @PathVariable Integer quantidade){
        return ResponseEntity.ok(pedidoService.addItem(id, itemId, quantidade));
    }

    @PutMapping("/{id}/itens/{itemId}/reduzir/{quantidade}")
    public ResponseEntity<PedidoDTOResponse> reduzirItem(@PathVariable Long id, @PathVariable Long itemId,
                                                         @PathVariable Integer quantidade){
        return ResponseEntity.ok(pedidoService.decreaseItemQuantity(id, itemId, quantidade));
    }

    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Long id) {
        pedidoService.deleteById(id);
    }
}
