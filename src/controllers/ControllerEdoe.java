package controllers;

import java.io.IOException;
import java.io.Serializable;

public class ControllerEdoe implements Serializable {
	private ControllerUsuarios controllerUsuario = new ControllerUsuarios();
	private ControllerDescritor controllerDescritor = new ControllerDescritor();
	private ControllerDoacao doacoes = new ControllerDoacao();
	
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
		return controllerUsuario.listaDescritorDeItensParaDoacao(controllerDescritor.getDescritores());
	}
	
	public String listaItensParaDoacao() {
		return controllerUsuario.listaItensParaDoacao();
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
 		return doacoes.adicionaDoacao(controllerUsuario.realizaDoacao(idItemNecessario, idItemDoado, data));
 	}
 	
	public String listaDoacoes() {
 		return doacoes.listaDoacoes();
 	}
	
//	public void finalizaSistema() throws IOException {
//		FileOutputStream arquivo = new FileOutputStream("sistema_serializado/objetos.ser");
// 		ObjectOutputStream oos = new ObjectOutputStream(arquivo);
// 		oos.writeObject(doacoes);
// 		oos.writeObject(controllerDescritor);
// 		oos.writeObject(controllerUsuario);
// 		oos.close();
//	}
//	
//	public void iniciaSistema() throws IOException {
// 		FileInputStream arquivo = new FileInputStream("sistema_serializado/objetos.ser");
// 		ObjectInputStream ois = new ObjectInputStream(arquivo);
// 		
//	}

}
