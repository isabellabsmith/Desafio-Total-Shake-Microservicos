package br.com.desafio.totalshake.application.DTO;

import br.com.desafio.totalshake.domain.model.FormaDePagamento;
import br.com.desafio.totalshake.domain.model.Pagamento;
import org.hibernate.annotations.Check;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class FormaDePagamentoDTO {
    @Enumerated(EnumType.STRING)
    private FormaDePagamento formaDePagamento;

    FormaDePagamentoDTO() {}

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public void updateFormaDePagamento(Pagamento pagamento) {
        pagamento.setFormaDePagamento(formaDePagamento);
    }
}
