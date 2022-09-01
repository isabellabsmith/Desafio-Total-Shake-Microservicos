package br.com.desafio.totalshake.application.controller;

import br.com.desafio.totalshake.PedidoEndpoint;
import br.com.desafio.totalshake.application.DTO.FormaDePagamentoDTO;
import br.com.desafio.totalshake.application.DTO.PagamentoDTOPost;
import br.com.desafio.totalshake.application.DTO.PagamentoDTOResponse;
import br.com.desafio.totalshake.domain.model.FormaDePagamento;
import br.com.desafio.totalshake.domain.model.Pagamento;
import br.com.desafio.totalshake.domain.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import java.util.List;

@RestController
public class PagamentoController {

    @Autowired
    PedidoEndpoint pedidoEndpoint;

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping("/pagamento")
    public List<Pagamento> findAll(){
        return pagamentoService.findAll();
    }

    @GetMapping("/pagamento/{id}")
    public ResponseEntity<PagamentoDTOResponse> buscarPagamento(@PathVariable Long id){
        return ResponseEntity.ok(new PagamentoDTOResponse(pagamentoService.findPagamentoById(id)));
    }

    @PostMapping("/pagamento")
    public ResponseEntity<PagamentoDTOResponse> criarPagamento(@RequestBody @Valid PagamentoDTOPost pagamentoPostDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pagamentoService.createPagamento(pagamentoPostDTO));
    }

    @PutMapping("/pagamento/cancelar/{id}")
    public ResponseEntity<PagamentoDTOResponse> cancelarPagamento(@PathVariable Long id){
        return ResponseEntity.ok(pagamentoService.cancelPagamento(id));
    }

    @PutMapping("/pagamento/alterar/{id}")
    public ResponseEntity<PagamentoDTOResponse> mudarFormaDePagamento(@PathVariable Long id, @RequestBody FormaDePagamentoDTO formaDePagamento) {
        return ResponseEntity.ok(pagamentoService.changeFormaDePagamento(id, formaDePagamento));
    }

    @PutMapping("/pagamento/confirmar/{id}")
    public ResponseEntity<PagamentoDTOResponse> confirmarPagamento(@PathVariable Long id){
        Pagamento pagamento = pagamentoService.findPagamentoById(id);
        pedidoEndpoint.mudarStatusPedidoPago(pagamento.getPedidoId());
        return ResponseEntity.ok(pagamentoService.confirmPedido(id));
    }

    @DeleteMapping("/pagamento/delete/{id}")
    public void deletePagamento(@PathVariable Long id) {
        pagamentoService.deleteById(id);
    }

}
