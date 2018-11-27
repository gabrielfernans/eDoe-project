package controllers;

import java.util.ArrayList;
import java.util.Collections;
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
		String aux = "";
			
		for (Usuario usuario : map.values()) {
			List<Item> listDeItens= new ArrayList<Item>();	
			for (Item itens : (usuario.getListaItens().values())) {
				listDeItens.add(itens);			
			}
			
			Collections.sort(listDeItens, new ItemComparavelNome());
			
			for (Item itm : listDeItens) {
				aux += itm.retornaDescricaoEQuantidade() + " | ";
			}
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
}	
