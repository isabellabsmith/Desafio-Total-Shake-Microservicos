package br.com.desafio.totalshake.application.controller;

import br.com.desafio.totalshake.proxy.PedidoProxy;
import br.com.desafio.totalshake.application.DTO.FormaDePagamentoDTO;
import br.com.desafio.totalshake.application.DTO.PagamentoDTOPost;
import br.com.desafio.totalshake.application.DTO.PagamentoDTOResponse;
import br.com.desafio.totalshake.domain.model.Pagamento;
import br.com.desafio.totalshake.domain.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/pagamento")
public class PagamentoController {

    @Autowired
    PedidoProxy pedidoProxy;

    @Autowired
    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping
    public List<Pagamento> findAll(){
        return pagamentoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTOResponse> buscarPagamento(@PathVariable Long id){
        return ResponseEntity.ok(new PagamentoDTOResponse(pagamentoService.findPagamentoById(id)));
    }

    @PostMapping
    public ResponseEntity<PagamentoDTOResponse> criarPagamento(@RequestBody @Valid PagamentoDTOPost pagamentoPostDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pagamentoService.createPagamento(pagamentoPostDTO));
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<PagamentoDTOResponse> cancelarPagamento(@PathVariable Long id){
        return ResponseEntity.ok(pagamentoService.cancelPagamento(id));
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<PagamentoDTOResponse> mudarFormaDePagamento(@PathVariable Long id, @RequestBody FormaDePagamentoDTO formaDePagamento) {
        return ResponseEntity.ok(pagamentoService.changeFormaDePagamento(id, formaDePagamento));
    }

    @PutMapping("/confirmar/{id}")
    public ResponseEntity<PagamentoDTOResponse> confirmarPagamento(@PathVariable Long id){
        Pagamento pagamento = pagamentoService.findPagamentoById(id);
        pedidoProxy.mudarStatusPedidoPago(pagamento.getPedidoId());
        return ResponseEntity.ok(pagamentoService.confirmPedido(id));
    }

    @DeleteMapping("/{id}")
    public void deletePagamento(@PathVariable Long id) {
        pagamentoService.deleteById(id);
    }

}
