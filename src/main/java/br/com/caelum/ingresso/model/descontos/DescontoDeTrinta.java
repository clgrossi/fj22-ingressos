package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;

public class DescontoDeTrinta implements Desconto {

	private BigDecimal percentualDeDesconto=new BigDecimal("0.3");

	@Override
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
		return precoOriginal.subtract(trintaPorCentoSobre(precoOriginal));
	}

	private BigDecimal trintaPorCentoSobre(BigDecimal precoOriginal) {
		BigDecimal x=precoOriginal.multiply(percentualDeDesconto);
		return x;
	}

	@Override
	public String getDescricao() {
		return "Desconto Banco";
	}

	public BigDecimal getPercentualDeDesconto() {
		return percentualDeDesconto;
	}

	public void setPercentualDeDesconto(BigDecimal percentualDeDesconto) {
		this.percentualDeDesconto = percentualDeDesconto;
	}
	
	
	
	
	
}
