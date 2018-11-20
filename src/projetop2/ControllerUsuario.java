package projetop2;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ControllerUsuario {

	private Map<String, Usuario> usuarios = new HashMap<>();
	
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		if(usuarios.containsKey(id))
			throw new IllegalArgumentException();
		switch(classe) {
		case "PESSOA_FISICA":
			usuarios.put(id, new PessoaFisica(id, nome, email, celular, classe, "doador"));
			break;
		default:
			usuarios.put(id, new PessoaJuridica(id, nome, email, celular, classe, "doador"));
			break;
		}
		return id;
	}
	
	public String pesquisaUsuarioPorId(String id) {
		if(!usuarios.containsKey(id))
			throw new IllegalArgumentException();
		return usuarios.get(id).toString();
	}
	
	public void removeUsuario(String id) {
		if(!usuarios.containsKey(id))
			throw new IllegalArgumentException();
		usuarios.remove(id);
	}
	
	public void lerReceptores(String caminho) {
		
	}
	
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return usuarios.get(id).atualizaUsuario(nome, email, celular);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
