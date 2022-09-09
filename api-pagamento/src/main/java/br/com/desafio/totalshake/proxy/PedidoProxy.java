package br.com.desafio.totalshake.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="pedido")
public interface PedidoProxy {

    @GetMapping("/pedido/pago/{id}")
    void mudarStatusPedidoPago(@PathVariable Long id);
}
