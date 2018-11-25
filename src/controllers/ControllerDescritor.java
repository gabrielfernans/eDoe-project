package controllers;

import java.util.HashSet;
import java.util.Set;
import entidades.Descritor;

/**
 * Classe que representa o controlador dos descritores cadastrados no sistema.
 * @author
 *
 */
public class ControllerDescritor {
	private Set<Descritor> descritores = new HashSet<>();
	
	/**
	 * Construtor da classe ControllerDescritor
	 */
	public ControllerDescritor() {
		this.descritores = new HashSet<>();
	}

	/**
	 * Método responsável por cadastrar um descritor no sistema. Contém uma exceção para verificar se o parâmetro inserido
	 * não irá interferir no funcionamento do programa.
	 * @param descritor Nome do descritor
	 */
	public void cadastraDescritor(String descritor) {
		Descritor novoDescritor = new Descritor(descritor);
		
		if (descritor == null || descritor.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}
		
		if (descritores.contains(novoDescritor)) {
			throw new IllegalArgumentException("Descritor de Item ja existente: " + novoDescritor.getDescritor().trim().toLowerCase() + ".");
		}
		descritores.add(novoDescritor);
	}
	
	
}	
