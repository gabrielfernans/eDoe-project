package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entidades.DoacaoComparavelPorData;

public class ControllerDoacao {
	private List<String> doacoes = new ArrayList<String>();
	
	public void adicionaDoacao(String doacao) {
		this.doacoes.add(doacao);
	}
	
	public String listaDoacoes() {
		String doacoesParaExibicao = "";		
		Collections.sort(doacoes, new DoacaoComparavelPorData());
		for(String doacao : doacoes) {
			doacoesParaExibicao += doacao + " | ";
		}
		return doacoesParaExibicao.substring(0, doacoesParaExibicao.length() - 3);
	}

}
