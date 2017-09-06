package br.com.caelum.ingresso.validacao;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {

	private List<Sessao> sessoesDaSala;
	
	public GerenciadorDeSessao(List<Sessao> sessoesDaSala){
		this.sessoesDaSala=sessoesDaSala;
	}
	
	public boolean cabe(final Sessao sessaoAtual) {
		Optional<Boolean> optionalCabe = sessoesDaSala
										.stream()
										.map(sessaoExistente->horarioEhValido(sessaoExistente,sessaoAtual))
										.reduce(Boolean::logicalAnd);
		
		return optionalCabe.orElse(true);
	}
	
	private boolean horarioEhValido(Sessao sessaoExistente, Sessao sessaoAtual) {
		LocalTime h1 = sessaoExistente.getHorario();
		LocalTime h2 = sessaoAtual.getHorario();
		
		boolean ehAntes = h1.isBefore(h2);
		if (ehAntes) {
			return sessaoExistente.getHorarioTermino().isBefore(h2);
		} else {
			return sessaoAtual.getHorarioTermino().isBefore(h1);
		}
	}
	
}
