package br.com.desafio.totalshake;

import br.com.desafio.totalshake.application.DTO.PagamentoDTOPost;
import br.com.desafio.totalshake.application.DTO.PagamentoDTOResponse;
import br.com.desafio.totalshake.application.exception.PagamentoNaoExisteException;
import br.com.desafio.totalshake.domain.model.FormaDePagamento;
import br.com.desafio.totalshake.domain.model.Pagamento;
import br.com.desafio.totalshake.domain.model.Status;
import br.com.desafio.totalshake.domain.repository.PagamentoRepository;
import br.com.desafio.totalshake.domain.service.PagamentoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
class TotalshakeApplicationTests {
	@Mock
	private PagamentoRepository pagamentoRepository;

	@InjectMocks
	private PagamentoService pagamentoService;
	@Test
	void should_CreateNewPagamento_When_CorrectInput() {
		PagamentoDTOPost pagamento = new PagamentoDTOPost(BigDecimal.ONE, "Nome Teste", "1234567890000", "06/30","123", 1L, FormaDePagamento.PIX);

		PagamentoDTOResponse response = pagamentoService.createPagamento(pagamento);

		assertEquals(pagamento.getValor(), response.getValor());
		assertEquals(pagamento.getNome(), response.getNome());
		assertEquals(pagamento.getFormaDePagamento(), response.getFormaDePagamento());
		assertEquals(pagamento.getPedidoId(), response.getPedidoId());
	}

	@Test
	void should_FindAllPagamentos_When_PagamentosExist() {
		List<Pagamento> list = new ArrayList<>();
		Pagamento p1 = new Pagamento(1L, BigDecimal.ONE, "abc", "12345678900000", "06/30", "123", Status.CRIADO, 1L, FormaDePagamento.PIX);
		Pagamento p2 = new Pagamento(2L, BigDecimal.ONE, "abcd", "12345678911111", "06/25", "421", Status.CRIADO, 1L, FormaDePagamento.DINHEIRO);
		Pagamento p3 = new Pagamento(3L, BigDecimal.ONE, "abcef", "12345678955555", "10/30", "542", Status.CRIADO, 1L, FormaDePagamento.CARTAO_CREDITO);

		list.add(p1);
		list.add(p2);
		list.add(p3);

		when(pagamentoService.findAll()).thenReturn(list);

		List<Pagamento> empList = pagamentoService.findAll();

		assertEquals(3, empList.size());
		verify(pagamentoRepository, times(1)).findAll();
	}

	@Test
	void should_ThrowException_When_TryingToFindPagamentoThatDoesNotExist() throws PagamentoNaoExisteException {
		assertThatThrownBy(() -> pagamentoService.findPagamentoById(1L))
				.isInstanceOf(PagamentoNaoExisteException.class);
	}

	@Test
	void should_FindPagamento_When_PagamentoExists() {
		when(pagamentoRepository.findById(1L)).thenReturn(Optional.of(new Pagamento(1L, BigDecimal.ONE, "abcd", "12345678911111", "06/25", "421", Status.CRIADO, 1L, FormaDePagamento.DINHEIRO)));

		Pagamento p1 = pagamentoService.findPagamentoById(1L);

		assertEquals("abcd", p1.getNome());
		assertEquals("06/25", p1.getExpiracao());
		assertEquals("421", p1.getCodigo());
	}
}
