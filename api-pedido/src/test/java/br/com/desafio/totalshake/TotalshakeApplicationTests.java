package br.com.desafio.totalshake;

import br.com.desafio.totalshake.application.request.ItemPedidoDTO;
import br.com.desafio.totalshake.application.request.PedidoDTOPost;
import br.com.desafio.totalshake.domain.model.ItemPedido;
import br.com.desafio.totalshake.domain.model.Pedido;
import br.com.desafio.totalshake.domain.model.Status;
import br.com.desafio.totalshake.domain.repository.PedidoRepository;
import br.com.desafio.totalshake.domain.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TotalshakeApplicationTests {
	@MockBean
	private PedidoRepository pedidoRepository;

	@InjectMocks
	private PedidoService pedidoService;
	@BeforeEach
	void setup() {
		this.pedidoService = new PedidoService(pedidoRepository);
	}

	@Test
	void should_CreateNewPedido_When_CorrectInput() {
		List<ItemPedidoDTO> itens = new ArrayList<>();
		itens.add(new ItemPedidoDTO("abc", 12));
		itens.add(new ItemPedidoDTO("abcd", 5));
		itens.add(new ItemPedidoDTO("abcef", 3));
		PedidoDTOPost pedidoRequest = new PedidoDTOPost(itens);

		when(pedidoService.savePedido(pedidoRequest)).thenReturn(pedidoRequest.toPedidoModel());
		Pedido novoPedido = pedidoService.savePedido(pedidoRequest);
		assertEquals(pedidoRequest.getItens().size(), novoPedido.getItens().size());
		assertNotNull(novoPedido);
	}
}
