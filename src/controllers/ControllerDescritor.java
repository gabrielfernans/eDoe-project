package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Classe que representa o controlador dos descritores cadastrados no sistema.
 * @author
 *
 */
public class ControllerDescritor {
	
	private Set<String> descritores = new HashSet<>();

	/**
	 * Metodo responsavel por cadastrar um descritor no sistema. Contem uma excecao para verificar se o parametro inserido
	 * nao ira interferir no funcionamento do programa.
	 * @param descritor Nome do descritor
	 */
	public void cadastraDescritor(String descritor) {
		
		if (descritor == null || descritor.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		
		if (descritores.contains(descritor.trim().toLowerCase()))
			throw new IllegalArgumentException("Descritor de Item ja existente: " + descritor.trim().toLowerCase() + ".");
		
		descritores.add(descritor.trim().toLowerCase());
	}
	
	/**
	 * 
	 * @param descritor
	 * @return
	 */
	public boolean contemDescritor(String descritor) {
			return descritores.contains(descritor);
	}
	
	public final Set<String> getDescritores() {
		return descritores;
	}
}	