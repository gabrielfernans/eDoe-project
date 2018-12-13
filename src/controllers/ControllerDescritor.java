package controllers;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe que representa o controlador dos descritores cadastrados no sistema.
 * @author
 *
 */
public class ControllerDescritor implements Serializable{
	
	private Set<String> descritores = new HashSet<>();

	/**
	 * Metodo responsavel por cadastrar um descritor no sistema. Contem uma excecao para verificar se o parametro inserido
	 * nao ira interferir no funcionamento do programa.
	 * @param descritor nome do descritor.
	 */
	public void cadastraDescritor(String descritor) {
		
		if (descritor == null || descritor.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		
		if (descritores.contains(descritor.trim().toLowerCase()))
			throw new IllegalArgumentException("Descritor de Item ja existente: " + descritor.trim().toLowerCase() + ".");
		
		descritores.add(descritor.trim().toLowerCase());
	}
	
	/**
	 * Metodo responsavel por verificar se, dado um nome de descritor, ele contem esse descritor no sistema.
	 * @param descritor.
	 * @return um valor booleano, sendo true se o descritor existir no sistema e false se nao existir.
	 */
	public boolean contemDescritor(String descritor) {
			return descritores.contains(descritor);
	}
	
	/**
	 * Metodo responsavel por retornar o set de descritores.
	 * @return um set dos descritores existentes no sistema.
	 */
	public final Set<String> getDescritores() {
		return descritores;
	}
}