package fachada;

import java.io.IOException;

import controllers.ControllerDescritor;
import controllers.ControllerUsuario;
import easyaccept.EasyAccept;
import entidades.Descritor;

public class Fachada {

	ControllerUsuario controllerUsuario = new ControllerUsuario();
	ControllerDescritor controllerDescritor = new ControllerDescritor();
	
	public static void main(String[] args) {
		args = new String[] {"fachada.Fachada", "accept_testes/use_case_1.txt", "accept_testes/use_case_2.txt"};
		EasyAccept.main(args);
	}
	
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return controllerUsuario.adicionaDoador(id, nome, email, celular, classe);
	}
	
	public String pesquisaUsuarioPorId(String id) {
		return controllerUsuario.pesquisaUsuarioPorId(id);
	}
	
	public String pesquisaUsuarioPorNome(String nome) {
		return controllerUsuario.pesquisaUsuarioPorNome(nome);
	}
	
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return controllerUsuario.atualizaUsuario(id, nome, email, celular);
	}
	
	public void removeUsuario(String id) {
		controllerUsuario.removeUsuario(id);
	}
	
	public void lerReceptores(String caminho) throws IOException{
		controllerUsuario.lerReceptores(caminho);
	}
	
	public void cadastraDescritor(String descritor) {
		controllerDescritor.cadastraDescritor(new Descritor(descritor));
	}
	
	public void cadastraItem() {
		
	}
	
	public void exibeItem() {
		
	}
	
	public void atualizaItem() {
		
	}
	
	public void removeItem() {
		
	}
}
