package projetop2;

import java.util.HashSet;
import java.util.Set;

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
}	
