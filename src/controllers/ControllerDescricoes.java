package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import entidades.Descricao;
import entidades.Item;
import entidades.ItemComparavel;
import entidades.Usuario;


public class ControllerDescricoes {

	private Set<Descricao> descritores = new HashSet<>();
	
	public void cadastraDescritor(String descricao) {
		if (!descritores.contains(descricao)) {
			descritores.add(new Descricao(descricao.trim().toLowerCase()));
		}
		else {
			throw new IllegalArgumentException("");
		}
	}
	
	//método de listagem de todos os itens inseridos no sistema ordenada pela quantidade do item no sistema
	public String listagemPorItem(Map<String, Usuario> map) {
		String aux = "";
		ArrayList<Item> listDeItens= new ArrayList<Item>();		
		ArrayList<String> descricoesOrdenadas = new ArrayList<String>();
		
		for (Usuario usuario : map.values()) {
			for (Item itens : (usuario.getListaItens().values())) {
				listDeItens.add(itens);
				
				}
			
			Collections.sort(listDeItens, new ItemComparavel());
			
			for (Item itm : listDeItens) {
				aux += itm.toStringCombo() + " | ";
			}
		}
		
		return "";
	}
	
	// método de listagem de todos os descritores de itens cadastrados no sistema
	public String listagemPorQuantidadeEDescricao(Map<String, Usuario> map) {
		String retornoDaListagem = "";
		Set<String> setDeItens= new HashSet<String>();
		ArrayList<String> listDeItens= new ArrayList<String>();		
		ArrayList<String> descricoesOrdenadas = new ArrayList<String>();
		
		for (Descricao descricao : descritores) {
			descricoesOrdenadas.add(descricao.getDescricao());
		}
		Collections.sort(descricoesOrdenadas);
		
		for (String descricao : descricoesOrdenadas) {
			int count = 0;
			for (Usuario usuario : map.values()) {
				for (Item itens : (usuario.getListaItens().values())) {
					if (itens.getDescricao().equals(descricao)){
						count += itens.getQuantidade();
					}
				}
			}
			retornoDaListagem += count + " - " + descricao + " | " ;
			
		}

		return retornoDaListagem;
	}
	
}	
