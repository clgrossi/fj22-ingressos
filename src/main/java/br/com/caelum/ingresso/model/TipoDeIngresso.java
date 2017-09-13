package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.descontos.Desconto;
import br.com.caelum.ingresso.model.descontos.DescontoDeTrinta;
import br.com.caelum.ingresso.model.descontos.DescontoEstudante;
import br.com.caelum.ingresso.model.descontos.SemDesconto;

public enum TipoDeIngresso {
	INTEIRO(new SemDesconto()),
	ESTUDANTE(new DescontoEstudante()),
	BANCO(new DescontoDeTrinta());
	
	private final Desconto desconto;	//final - constante(o valor nao sera alterado)
	
	TipoDeIngresso(Desconto d) {
		this.desconto=d;
	}

	public BigDecimal aplicaDesconto(BigDecimal v) {
		return desconto.aplicarDescontoSobre(v);
	}
	
	public String getDescricao() {
		return desconto.getDescricao();
	}
	
}
