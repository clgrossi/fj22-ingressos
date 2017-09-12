package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
		BigDecimal precoFilme = new BigDecimal("2.33");
		BigDecimal precoSala = new BigDecimal("1.58");
		Filme f1 = new Filme("Harry Potter",Duration.ofMinutes(120),"Aventura", precoFilme);
		Sala sala = new Sala("3D", precoSala);
		
		BigDecimal soma= f1.getPreco().add(sala.getPreco());
		
		Sessao sessao = new Sessao(duasEMeia,f1,sala);
		
		//System.out.print(soma+"|"+sessao.getPreco()+"|"+f1.getPreco()+"|"+sala.getPreco());
		
		Assert.assertEquals(soma, sessao.getPreco());
	}

	@Test
	public void descontoBanco() {
		Sala sala = new Sala("3D", new BigDecimal("6.00"));
		Filme filme = new Filme("Harry Potter",Duration.ofMinutes(120),"Aventura", new BigDecimal("4.00"));
		Sessao sessao = new Sessao(LocalTime.now(),filme,sala);
		Ingresso ingresso = new Ingresso(sessao,TipoDeIngresso.BANCO, null);
		
		System.out.println("descontoBanco: "+ingresso.getPreco());

		BigDecimal precoEsperado = new BigDecimal("7.000");
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
	
	@Test
	public void descontoEstudante() {
		Sala sala = new Sala("3D", new BigDecimal("6.00"));
		Filme filme = new Filme("Harry Potter",Duration.ofMinutes(120),"Aventura", new BigDecimal("4.00"));
		Sessao sessao = new Sessao(LocalTime.now(),filme,sala);
		Ingresso ingresso = new Ingresso(sessao,TipoDeIngresso.ESTUDANTE, null);

		BigDecimal precoEsperado = new BigDecimal("5.0");
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
	
	@Test
	public void semDesconto() {
		Sala sala = new Sala("3D", new BigDecimal("6.00"));
		Filme filme = new Filme("Harry Potter",Duration.ofMinutes(120),"Aventura", new BigDecimal("4.00"));
		Sessao sessao = new Sessao(LocalTime.now(),filme,sala);
		Ingresso ingresso = new Ingresso(sessao,TipoDeIngresso.INTEIRO, null);

		System.out.println("semDesconto: "+ingresso.getPreco());
		
		BigDecimal precoEsperado = new BigDecimal("10.00");
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
	
	@Test
	public void garanteQueOLugarA1EstaOcupadoEOsLugaresA2EA3Disponiveis() {
		Lugar a1 = new Lugar("A",1);
		Lugar a2 = new Lugar("A",2);
		Lugar a3 = new Lugar("A",3);
		
		Filme filme = new Filme("Harry Potter",Duration.ofMinutes(120),"Aventura", new BigDecimal("4.00"));
		Sala sala = new Sala("3D", new BigDecimal("4.00"));
		Sessao sessao = new Sessao(LocalTime.now(),filme,sala);
		Ingresso ingresso = new Ingresso(sessao,TipoDeIngresso.INTEIRO, null);
		Set<Ingresso> ingressos = Stream.of(ingresso).collect(Collectors.toSet());
		
		sessao.setIngressos(ingressos);
		
		Assert.assertFalse(sessao.isDisponivel(a1));
		Assert.assertTrue(sessao.isDisponivel(a2));
		Assert.assertTrue(sessao.isDisponivel(a3));
		
		
	}
	
}
