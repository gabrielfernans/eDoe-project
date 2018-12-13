package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import entidades.DoacaoComparavelPorData;

/**
 * Classe que representa o controlador das doacoes realizadas no sistema.
 * @author
 *
 */
public class ControllerDoacao implements Serializable{
	private List<String> doacoes = new ArrayList<String>();
	
	/**
	 * Metodo responsavel adicionar doacao no sistema, quando ela for realizada.
	 * @param doacao o toString da doacao.
	 * @return a string que representa a doacao.
	 */
	public String adicionaDoacao(String doacao) {
		if (doacao == null || doacao.trim().equals("")) 
			throw new IllegalArgumentException("Doacao nao pode ser vazia ou nula");
		
		this.doacoes.add(doacao);
		return doacao;
	}
	
	/**
	 * Metodo responsavel por listar todas as doacoes do sistema.
	 * @return a string da lista das doacoes.
	 */
	public String listaDoacoes() {
		String doacoesParaExibicao = "";		
		Collections.sort(doacoes, new DoacaoComparavelPorData());
		for(String doacao : doacoes) {
			doacoesParaExibicao += doacao + " | ";
		}
		return doacoesParaExibicao.substring(0, doacoesParaExibicao.length() - 3);
	}
	
}
