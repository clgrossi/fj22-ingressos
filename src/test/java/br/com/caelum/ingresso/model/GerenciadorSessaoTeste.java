package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

public class GerenciadorSessaoTeste {

	@Test
	public void deveRetornarFalseQndNaoCabe() {
		LocalTime duasEMeia = LocalTime.of(14, 30);
		Filme f1 = new Filme("Harry Potter",Duration.ofMinutes(120),"Aventura", BigDecimal.ONE);
		Sala sala = new Sala("3D", BigDecimal.ONE);
		
		Sessao sessao = new Sessao(duasEMeia,f1,sala);
		
		List<Sessao> sessoesExistentes = Arrays.asList(
			new Sessao(LocalTime.of(13, 00),new Filme("Filme 1",Duration.ofMinutes(120),"Aventura", BigDecimal.ONE),sala),
			new Sessao(LocalTime.of(20, 00),new Filme("Filme 2",Duration.ofMinutes(90),"Aventura", BigDecimal.ONE),sala),
			new Sessao(LocalTime.of(10, 00),new Filme("Filme 3",Duration.ofMinutes(150),"Aventura", BigDecimal.ONE),sala));
		
		
		GerenciadorSessao g = new GerenciadorSessao(sessoesExistentes);
		Assert.assertFalse(g.cabe(sessao));
		
		Stream<Sessao> x=sessoesExistentes.stream();
	}
	
	@Test
	public void listaVazia() {

		LocalTime duasEMeia = LocalTime.of(14, 30);
		Filme f1 = new Filme("Harry Potter",Duration.ofMinutes(120),"Aventura", BigDecimal.ONE);
		Sala sala = new Sala("3D", BigDecimal.ONE);
		
		Sessao sessao = new Sessao(duasEMeia,f1,sala);

		List<Sessao> sessoesExistentes = null;

		/*sessoesExistentes = Arrays.asList(
				new Sessao(LocalTime.of(13, 00),new Filme("Filme 1",Duration.ofMinutes(120),"Aventura"),sala),
				new Sessao(LocalTime.of(20, 00),new Filme("Filme 2",Duration.ofMinutes(90),"Aventura"),sala),
				new Sessao(LocalTime.of(10, 00),new Filme("Filme 3",Duration.ofMinutes(150),"Aventura"),sala));
		*/
		
		GerenciadorSessao g = new GerenciadorSessao(sessoesExistentes);
		Assert.assertTrue(g.listaVazia(sessao));
		
	}
	
	
	@Test
	public void precoSessao() {
		LocalTime duasEMeia = LocalTime.of(14, 30);
		BigDecimal precoFilme = new BigDecimal(2.35);
		BigDecimal precoSala = new BigDecimal(1.50);
		Filme f1 = new Filme("Harry Potter",Duration.ofMinutes(120),"Aventura", precoFilme);
		Sala sala = new Sala("3D", precoSala);
		
		BigDecimal soma= f1.getPreco().add(sala.getPreco());
		
		Sessao sessao = new Sessao(duasEMeia,f1,sala);
		
		System.out.print(soma+"|"+sessao.getPreco()+"|"+f1.getPreco()+"|"+sala.getPreco());
		
		Assert.assertEquals(soma, sessao.getPreco());
	}
	
}
