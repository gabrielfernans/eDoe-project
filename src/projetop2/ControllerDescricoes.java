package projetop2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


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
	
	// método que lista todos os descritores por ordem alfabetica
	// falta adicionar quantidade
	
	public String listagemDosDescritoresPorOrdemAlfabetica() {
		ArrayList<String> listaDescricao = new ArrayList<String>();
		for (Descricao descricao : descritores) {
			listaDescricao.add(descricao.getDescricao());
		}
		Collections.sort(listaDescricao);
		return listaDescricao.stream().map(c -> c.toString()).collect(Collectors.joining(" | "));
	}
	
	// método que lista todos os itens pela quantidade de itens e sua descrição
	// Entrei na lista de usuarios do controller, dps na lista de itens e por fim no toString do item
	
	public String listagemDosItensPorQuantidade(Map<String, Usuario> map) {
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
