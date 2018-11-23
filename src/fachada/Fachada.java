package fachada;

import java.io.IOException;

import controllers.ControllerDescricoes;
import controllers.ControllerUsuario;

public class Fachada {

	ControllerUsuario controllerUser = new ControllerUsuario();
	ControllerDescricoes controllerDescricao = new ControllerDescricoes();
	
	public void lerReceptores(String caminho) throws IOException{
		controllerUser.lerReceptores(caminho);
	}
	
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return controllerUser.adicionaDoador(id, nome, email, celular, classe);
	}
	
	public String pesquisaUsuarioPorId(String id) {
		return controllerUser.pesquisaUsuarioPorId(id);
	}
	
	public String pesquisaUsuarioPorNome(String nome) {
		return controllerUser.pesquisaUsuarioPorNome(nome);
	}
	
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return controllerUser.atualizaUsuario(id, nome, email, celular);
	}
	
	public void removeUsuario(String id) {
		controllerUser.removeUsuario(id);
	}
	
	public String listaDescritorDeItensParaDoacao() {
		return controllerDescricao.listagemPorQuantidadeEDescricao(controllerUser.getUsuarios());
	}
	
	public String listaItensParaDoacao() {
		return controllerDescricao.listagemPorItem(controllerUser.getUsuarios());
	}
	
	public String pesquisaItemParaDoacaoPorDescricao() {
		return "";
	}
	 
	
}
