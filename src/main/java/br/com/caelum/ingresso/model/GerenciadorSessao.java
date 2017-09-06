package br.com.caelum.ingresso.model;

import java.time.LocalTime;
import java.util.List;

public class GerenciadorSessao {

	private List<Sessao> sessoesExistentes;
	
	public GerenciadorSessao(List<Sessao> sessoes) {
		this.sessoesExistentes=sessoes;
	}
	
	public boolean cabe(Sessao sessao) {
		
		for (Sessao s: sessoesExistentes) {
			if (!horarioEhValido(sessao,s)) {
				return false;
			}
		}
		return true;
		
	}
	
	private boolean horarioEhValido(Sessao s1, Sessao s2) {
		LocalTime h1 = s1.getHorario();
		LocalTime h2 = s2.getHorario();
		
		boolean ehAntes = h1.isBefore(h2);
		if (ehAntes) {
			return s1.getHorarioTermino().isBefore(h2);
		} else {
			return s2.getHorarioTermino().isBefore(h1);
		}
	}
	
	
	
	public boolean listaVazia(Sessao s1) {
		if (this.sessoesExistentes==null) {
			return true;
		}
		return false;
	}
	
	
}
