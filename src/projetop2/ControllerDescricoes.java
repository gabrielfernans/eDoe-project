package projetop2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ControllerDescricoes {
	
	//erick
	private Usuario usuario;
	
	//erick
	private ControllerUsuario controllerUsuario;
	
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
	
	public String listagemDosItensPorQuantidade() {
		Set<String> setDeItens= new HashSet<String>();
		ArrayList<String> listDeItens= new ArrayList<String>();
		// fiz um set pois elementos repetidos não serão adicionados
		for (Usuario usuario : controllerUsuario.getUsuarios().values()) {
			for (Item itens : (usuario.getListaItens().values())) {
				setDeItens.add(itens.retornaDescricaoEQuantidade());
			}
		}
		for (String str : setDeItens) {
			listDeItens.add(str);
		}
		Collections.sort(listDeItens);
		return listDeItens.stream().map(c -> c.toString()).collect(Collectors.joining(" | "));
	}
	
}	
