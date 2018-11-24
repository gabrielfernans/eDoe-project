package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import entidades.Descricao;

public class ControllerDescritor {
	
	private Set<Descricao> descritores = new HashSet<>();
	
	public void cadastraDescritor(String descricao) {
		if (!descritores.contains(descricao)) {
			descritores.add(new Descricao(descricao.trim().toLowerCase()));
		}
		else {
			throw new IllegalArgumentException("");
		}
	}
	
//	public String representacaoDeTodosOsDescritores() {
//		ArrayList<Descricao> listaDescricao = new ArrayList<Descricao>();
//		for (Descricao descricao : descritores) {
//			listaDescricao.add(descricao.toString());
//		}
//		Collections.sort(listaDescricao);
//		return listaDescricao.stream().map(c -> c.toString()).collect(Collectors.joining(" | "));
//	}
}	
