package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
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
	
	public String listaDescritorDeItensParaDoacao(Map<String, Usuario> map) {

		ArrayList<String> listaOrdenada = new ArrayList<>();
		ArrayList<String> listaDescricoesOrdenadasDoSet = new ArrayList<>();
		ArrayList<String> listaDescricoesItens = new ArrayList<>();
		List<Item> listDeItens= new ArrayList<Item>();	
		boolean varBool = true;
		String aux = "";
		
		for (String descricao : descritores) {
			listaDescricoesOrdenadasDoSet.add(descricao);
		}
		
		for (Usuario usuario : map.values()) {
			for (Item itens : (usuario.getListaItens().values())) {
				listDeItens.add(itens);
			}
		}
		
		for (Item item : listDeItens) {
			listaDescricoesItens.add(item.getDescritor());
		}
		
		Collections.sort(listaDescricoesOrdenadasDoSet);
		Collections.sort(listaDescricoesItens);
		
		for (String descricao : listaDescricoesOrdenadasDoSet) {
			if (varBool == false) {
				listaOrdenada.add("0" + " - " + descricao);
				listaOrdenada.add(" | ");
			}
			varBool = true;
			for (String item : listaDescricoesItens) {
				if (item.equals(descricao)){
					for (Item itens : listDeItens) {
						if (itens.getDescritor().equals(item)) {
							listaOrdenada.add(itens.retornaDescricaoEQuantidade());
							listaOrdenada.add(" | ");
							break;
						}

					}

				} 
				else {
					varBool = false;
				}
			} 

		}

//		for (String descricao : listaDescricoesOrdenadasDoSet) {
//			for (Item item : listDeItens) {
//				if (item.getDescritor().equals(descricao)){
//					listaOrdenada.add(item.retornaDescricaoEQuantidade());
//					listaOrdenada.add(" | ");
//				}
//				else {
//					varBool = false;
//					itemAux = item;
//				}
//			}
//			if (varBool == false) {
//				listaOrdenada.add(itemAux.retornaDescricaoEQuantidade());
//				listaOrdenada.add(" | ");
//			}
//				
//		}

		listaOrdenada.remove(listaOrdenada.size()-1);	

		for (String str : listaOrdenada) {
			aux += str;
		}

		return aux;
	}

	
	public String listaItensParaDoacao(Map<String, Usuario> map) {
		String retornoDaListagem = "";	
		ArrayList<String> descricoesOrdenadas = new ArrayList<String>();
		
		for (String descricao : descritores) {
			descricoesOrdenadas.add(descricao);
		}
		Collections.sort(descricoesOrdenadas);
		
		for (String descricao : descricoesOrdenadas) {
			int count = 0;
			for (Usuario usuario : map.values()) {
				for (Item itens : (usuario.getListaItens().values())) {
					if (itens.getDescritor().equals(descricao)){
						count += itens.getQuantidade();
					}
				}
			}
			retornoDaListagem += count + " - " + descricao + " | " ;
			
		}

		return retornoDaListagem;
	}
	
	public String pesquisaItemParaDoacaoPorDescricao(String desc, Map<String, Usuario> map) {
		if (desc == null || desc.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
		}
		
		List<Item> listDeItens= new ArrayList<Item>();	
		
		for (Usuario usuario : map.values()) {
			for (Item itens : (usuario.getListaItens().values())) {
				listDeItens.add(itens);
			}
		}
		
		Collections.sort(listDeItens);
		
		String toString = "";
		for (Item itens : listDeItens) {
			if (itens.getDescritor().toLowerCase().equals(desc.toLowerCase())) {
				toString += itens.toStringDeTags() + " | ";
			}
		}
		return toString;
	}	
}	
