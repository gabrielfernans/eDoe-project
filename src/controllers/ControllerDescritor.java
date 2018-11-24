package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import entidades.Descritor;


public class ControllerDescritor {
	
	private Set<Descritor> descritores = new HashSet<>();
	
	/**
	 * 
	 * @param descritor
	 */
	public void cadastraDescritor(Descritor descritor) {
		if (!descritores.contains(descritor)) {
			descritores.add(descritor);
		}
		else {
			throw new IllegalArgumentException("");
		}
	}
	
	public String representacaoDeTodosOsDescritores() {
		ArrayList<String> listaDescritores = new ArrayList<String>();
		for (Descritor descricao : descritores) {
			listaDescritores.add(descricao.toString());
		}
		Collections.sort(listaDescritores);
		return listaDescritores.stream().map(c -> c.toString()).collect(Collectors.joining(" | "));
	}
}	
