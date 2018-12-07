package fachada;

import java.io.IOException;
import controllers.ControllerDescritor;
import controllers.ControllerUsuario;
import easyaccept.EasyAccept;

public class Fachada {
	
	ControllerUsuario controllerUsuario = new ControllerUsuario();
	ControllerDescritor controllerDescritor = new ControllerDescritor();
	
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
		if (controllerDescritor.contemDescritor(descricaoItem) == true) {
			return controllerUsuario.cadastraItem(idDoador, descricaoItem, quantidade, tags);
		}
		else {
			controllerDescritor.cadastraDescritor(descricaoItem);
			return controllerUsuario.cadastraItem(idDoador, descricaoItem, quantidade, tags);
			
		}
	}
	
	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		return controllerUsuario.cadastraItem(idReceptor, descricaoItem, quantidade, tags);
	}
	
	public String exibeItem(int idItem, String idDoador) {
		return controllerUsuario.exibeItem(idItem, idDoador);
	}
	
	public String listaItensNecessarios(){
		return controllerUsuario.listaItensNecessarios();
	}
	
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		return controllerUsuario.atualizaItem(idItem, idDoador, quantidade, tags);
	}
	
	public String atualizaItemNecessario(String idReceptor, int idItem, int quantidade, String tags) {
		return controllerUsuario.atualizaItem(idItem, idReceptor, quantidade, tags);
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
		return controllerUsuario.pesquisaItemParaDoacaoPorDescricao(desc);
	}

 	public void removeItemNecessario(String idReceptor, int idItem) {
		controllerUsuario.removeItem(idItem, idReceptor);
	}
 	
 	public String match(String idReceptor, int idItemNecessario) {
 		return controllerUsuario.match(idReceptor, idItemNecessario);
 	}
 	
 	public String realizaDoacao(int idItemNecessario, int idItemDoado, String data) {
 		return controllerUsuario.realizaDoacao(idItemNecessario, idItemDoado, data);
 	}
 	
 	public String listaDoacoes() {
 		return controllerUsuario.listaDoacoes();
 	}
 	
}