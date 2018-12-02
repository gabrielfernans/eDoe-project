package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import entidades.Item;
import entidades.ItemComparavel;
import entidades.Usuario;

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
	
	/**
	 * Metodo que lista os descritores de itens cadastrados no sistema, em ordem alfabetica pela descricao do mesmo.
	 * Na saida, eh exibido a quantidade do item e sua descricao.
	 * @param usuarios Um mapa com os usuarios que sera necessario para acessar as listas de itens dos usuarios.
	 * @return O retorno eh uma string com a representacao textual dos itens
	 */
	public String listaDescritorDeItensParaDoacao(Map<String, Usuario> usuarios) {
		Map<String, Integer> itens = new HashMap<String, Integer>();
		for (Usuario usuario : usuarios.values()) {
			for (Item item : (usuario.getItens().values())) {
				itens.put(item.getDescritor(), item.getQuantidade());
			}
		}
		for (String descricao : descritores) {
			if(!itens.containsKey(descricao))
				itens.put(descricao, 0);
		}
		List<String> itensOrdenados = new ArrayList<String>();
		itensOrdenados.addAll(itens.keySet());
		Collections.sort(itensOrdenados);
		
		String listaFinal = "";
		for(String descricao : itensOrdenados) {
			listaFinal += itens.get(descricao) + " - " + descricao + " | ";
		}
		return listaFinal.substring(0, listaFinal.length()-3);
	}

	/**
	 * Metodo que lista todos os itens inseridos no sistema ordenada pela quantidade do item no sistema.
	 * Itens com a mesma quantidade serao ordenados pela ordem alfabetica de descricao.
	 * @param usuarios Um mapa com os usuarios que sera necessario para acessar as listas de itens dos usuarios.
	 * @return O retorno eh uma string com a representacao do id do item, a descricao, tag, quantidade e o doador.
	 */
	public String listaItensParaDoacao(Map<String, Usuario> usuarios) {
		ArrayList<Item> itensOrdenados = new ArrayList<Item>();
		LinkedHashMap<String, Item> mapDeItens = new LinkedHashMap<String, Item>();
		HashSet<Item> setDeItens = new HashSet<Item>();
		
		String aux = "";
		
		for (Usuario usuario : usuarios.values()) { 
			for (Item itens : (usuario.getItens().values())) {
				setDeItens.add(itens);
			}
		}
		
		//addAll
		
		itensOrdenados.addAll(setDeItens);

		Collections.sort(itensOrdenados, new ItemComparavel());
		
		for (Item item : itensOrdenados) {
			mapDeItens.put(item.getDescritor(), item);
		}
		
		
		for (Item mapItensOrdenado : mapDeItens.values()) { 
			for (Usuario usuario : usuarios.values()) {
				for (Item itens : (usuario.getItens().values())) {	
					String str = "";
					if (mapItensOrdenado.equals(itens)) {
						str = mapItensOrdenado.toStringCombo() + "doador: " + usuario.getNome() + "/" + usuario.getId().replace(".", "").replace("-", "")  + " | ";
						aux += str;
						break;
					}
				}
			}
		}
		
		return aux.substring(0, aux.length()-3);
		
	}	
}	