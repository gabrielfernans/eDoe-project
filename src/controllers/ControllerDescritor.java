package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import entidades.Item;
import entidades.ItemComparavel;
import entidades.ItemComparavelNome;
import entidades.Usuario;

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
		descritores.add(descritor.trim().toLowerCase());
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
	
	public String listaDescritorDeItensParaDoacao(Map<String, Usuario> map) {
		Map<String, Integer> itens = new HashMap<String, Integer>();
		for (Usuario usuario : map.values()) {
			for (Item item : (usuario.getListaItens().values())) {
				itens.put(item.getDescritor(), item.getQuantidade());
			}
		}
		for (String descricao : descritores) {
			if(!itens.containsKey(descricao))
				itens.put(descricao, 0);
		}
		List<String> itensOrdenados = new ArrayList<String>();
		for(String descricao: itens.keySet()) {
			itensOrdenados.add(descricao);
		}
		Collections.sort(itensOrdenados);
		String listaFinal = "";
		for(String descricao : itensOrdenados) {
			listaFinal += itens.get(descricao) + " - " + descricao + " | ";
		}
		return listaFinal.substring(0, listaFinal.length()-3);
	}

	public String listaItensParaDoacao(Map<String, Usuario> map) {
		ArrayList<Item> itensOrdenados = new ArrayList<Item>();
		LinkedHashMap<String, Item> mapDeItens = new LinkedHashMap<String, Item>();
		HashSet<Item> setDeItens = new HashSet<Item>();
		String aux = "";
		
		for (Usuario usuario : map.values()) { 
			for (Item itens : (usuario.getListaItens().values())) {
				setDeItens.add(itens);
			}
		}
		
		for (Item itens : setDeItens) {
			itensOrdenados.add(itens);
		}

		Collections.sort(itensOrdenados, new ItemComparavel());
		
		for (Item itens : itensOrdenados) {
			mapDeItens.put(itens.getDescritor(), itens);
		}
		
		for (Usuario usuario : map.values()) { 
			for (Item mapItensOrdenado : mapDeItens.values()) {
				for (Item itens : (usuario.getListaItens().values())) {
					if (mapItensOrdenado.equals(itens)) {
						aux += mapItensOrdenado.toStringCombo() + "doador: " + usuario.getNome() + "/" + usuario.getId().replace(".", "").replace("-", "")  + " | ";
						break;
					}
				}
			}
		}
		
		return aux.substring(0, aux.length()-3);
		
	}
	
	public String pesquisaItemParaDoacaoPorDescricao(String desc, Map<String, Usuario> map) {
		if (desc == null || desc.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
		}
		
		List<Item> listDeItens= new ArrayList<Item>();
		HashSet<Item> setDeItens = new HashSet<Item>();
		
		
		for (Usuario usuario : map.values()) {
			for (Item itens : (usuario.getListaItens().values())) {
				setDeItens.add(itens);
			}
		}
		
		for (Item itens : setDeItens) {
			listDeItens.add(itens);
		}
		
		Collections.sort(listDeItens);
		
		String toString = "";
		for (Item itens : listDeItens) {
			if (itens.getDescritor().contains(desc))
				toString += itens.idItemToString() + " | ";
		}
		return toString.substring(0, toString.length()-3);
	}	
}	
