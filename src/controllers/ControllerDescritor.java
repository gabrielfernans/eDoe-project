package controllers;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe que representa o controlador dos descritores cadastrados no sistema.
 * @author
 *
 */
public class ControllerDescritor {
	private Set<String> descritores = new HashSet<>();
	
	/**
	 * Construtor da classe ControllerDescritor
	 */
	public ControllerDescritor() {
		this.descritores = new HashSet<>();
	}
	
	/**
	 * M�todo respons�vel por cadastrar um descritor no sistema. Cont�m uma exce��o para verificar se o par�metro inserido
	 * n�o ir� interferir no funcionamento do programa.
	 * @param descritor Nome do descritor
	 */
	public void cadastraDescritor(String descritor) {
		
		if (descritor == null || descritor.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}
		
		if (descritores.contains(descritor.trim().toLowerCase())) {
			throw new IllegalArgumentException("Descritor de Item ja existente: " + descritor.trim().toLowerCase() + ".");
		}
		descritores.add(descritor);
	}
	
	/**
	 * 
	 * @param descritor
	 * @return
	 */
	public boolean contemDescritor(String descritor) {
		if (descritores.contains(descritor)) {
			return true;
		}
		else {
			return false;
		}
	}
}	
