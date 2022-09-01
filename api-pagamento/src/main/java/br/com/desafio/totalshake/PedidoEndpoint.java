package br.com.desafio.totalshake;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="pedido", url="http://localhost:8080/pedidos")
public interface PedidoEndpoint {

    @GetMapping("/pago/{id}")
    void mudarStatusPedidoPago(@PathVariable Long id);
}
