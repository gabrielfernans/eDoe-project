package fachada;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import controllers.ControllerDescritor;
import controllers.ControllerUsuario;
import easyaccept.EasyAccept;
import entidades.Usuario;

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
	
	public void adicionaDescritor(String descritor) {
		controllerDescritor.cadastraDescritor(descritor);
	}
	
	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		return controllerUsuario.cadastraItem(idDoador, descricaoItem, quantidade, tags);
	}
	
	public String exibeItem(int idItem, String idDoador) {
		return controllerUsuario.exibeItem(idItem, idDoador);
	}
	
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		return controllerUsuario.atualizaItem(idItem, idDoador, quantidade, tags);
	}
	
	public void removeItemParaDoacao(int idItem, String idDoador) {
		controllerUsuario.removeItem(idItem, idDoador);
	}
	
	public String listaDescritorDeItensParaDoacao() {
		return controllerDescritor.listaDescritorDeItensParaDoacao(controllerUsuario.getUsuarios());
	}
	
	public String listaItensParaDoacao() {
		return controllerDescritor.listaItensParaDoacao(controllerUsuario.getUsuarios());
	}
	
	public String pesquisaItemParaDoacaoPorDescricao(String desc) {
		return controllerDescritor.pesquisaItemParaDoacaoPorDescricao(desc, controllerUsuario.getUsuarios());
	}
}
