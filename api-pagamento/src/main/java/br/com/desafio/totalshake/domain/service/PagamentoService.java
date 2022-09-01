package br.com.desafio.totalshake.domain.service;

import br.com.desafio.totalshake.application.DTO.FormaDePagamentoDTO;
import br.com.desafio.totalshake.application.DTO.PagamentoDTOPost;
import br.com.desafio.totalshake.application.DTO.PagamentoDTOResponse;
import br.com.desafio.totalshake.application.exception.PagamentoNaoExisteException;
import br.com.desafio.totalshake.domain.model.FormaDePagamento;
import br.com.desafio.totalshake.domain.model.Pagamento;
import br.com.desafio.totalshake.domain.model.Status;
import br.com.desafio.totalshake.domain.repository.PagamentoRepository;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @Transactional
    public PagamentoDTOResponse createPagamento(PagamentoDTOPost pagamentoDTOPost) {

        var pagamento = pagamentoDTOPost.toPagamento();

        pagamento.setStatus(Status.CRIADO);
        pagamentoRepository.save(pagamento);

        return new PagamentoDTOResponse(pagamento);
    }

    @Transactional
    public PagamentoDTOResponse changeFormaDePagamento(Long id, FormaDePagamentoDTO formaDePagamento){
        var pagamento = this.findPagamentoById(id);
        formaDePagamento.updateFormaDePagamento(pagamento);

        pagamentoRepository.save(pagamento);

        return new PagamentoDTOResponse(pagamento);
    }

    @Transactional
    public PagamentoDTOResponse confirmPedido(Long id) {
        var pagamento= this.findPagamentoById(id);
        pagamento.setStatus(Status.CONFIRMADO);
        pagamento = pagamentoRepository.save(pagamento);

        return new PagamentoDTOResponse(pagamento);
    }

    @Transactional
    public PagamentoDTOResponse cancelPagamento(Long id) {
        var pagamento= this.findPagamentoById(id);
        pagamento.setStatus(Status.CANCELADO);
        pagamento = pagamentoRepository.save(pagamento);

        return new PagamentoDTOResponse(pagamento);
    }

    @Transactional
    public Pagamento findPagamentoById(long id) throws PagamentoNaoExisteException {
        return pagamentoRepository
                .findById(id)
                .orElseThrow(PagamentoNaoExisteException::new);
    }

    @Transactional
    public List<Pagamento> findAll() {
        return pagamentoRepository.findAll();
    }

    @Transactional
    public void deleteById(long id) {
        var pagamento= this.findPagamentoById(id);
        pagamentoRepository.delete(pagamento);
    }
}
