package fachada;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import controllers.ControllerEdoe;

public class Fachada {
	
	ControllerEdoe controllerEdoe = new ControllerEdoe();
	
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return controllerEdoe.adicionaDoador(id, nome, email, celular, classe);
	}
	
	public String pesquisaUsuarioPorId(String id) {
		return controllerEdoe.pesquisaUsuarioPorId(id);
	}
	
	public String pesquisaUsuarioPorNome(String nome) {
		return controllerEdoe.pesquisaUsuarioPorNome(nome);
	}
	
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return controllerEdoe.atualizaUsuario(id, nome, email, celular);
	}
	
	public void removeUsuario(String id) {
		controllerEdoe.removeUsuario(id);
	}
	
	public void lerReceptores(String caminho) throws IOException{
		controllerEdoe.lerReceptores(caminho);
	}
	
	public void adicionaDescritor(String descritor) {
		controllerEdoe.adicionaDescritor(descritor);
	}
	
	public int adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
		return controllerEdoe.adicionaItemParaDoacao(idDoador, descricaoItem, quantidade, tags);
	}
	
	public int adicionaItemNecessario(String idReceptor, String descricaoItem, int quantidade, String tags) {
		return controllerEdoe.adicionaItemNecessario(idReceptor, descricaoItem, quantidade, tags);
	}
	
	public String exibeItem(int idItem, String idDoador) {
		return controllerEdoe.exibeItem(idItem, idDoador);
	}
	
	public String listaItensNecessarios(){
		return controllerEdoe.listaItensNecessarios();
	}
	
	public String atualizaItemParaDoacao(int idItem, String idDoador, int quantidade, String tags) {
		return controllerEdoe.atualizaItemParaDoacao(idItem, idDoador, quantidade, tags);
	}
	
	public String atualizaItemNecessario(String idReceptor, int idItem, int quantidade, String tags) {
		return controllerEdoe.atualizaItemNecessario(idReceptor, idItem,  quantidade, tags);
	}
	
	public void removeItemParaDoacao(int idItem, String idDoador) {
		controllerEdoe.removeItemParaDoacao(idItem, idDoador);
	}
	
	public String listaDescritorDeItensParaDoacao() {
		return controllerEdoe.listaDescritorDeItensParaDoacao();
	}
	
	public String listaItensParaDoacao() {
		return controllerEdoe.listaItensParaDoacao();
	}
	
	public String pesquisaItemParaDoacaoPorDescricao(String desc) {
		return controllerEdoe.pesquisaItemParaDoacaoPorDescricao(desc);
	}

 	public void removeItemNecessario(String idReceptor, int idItem) {
 		controllerEdoe.removeItemNecessario(idReceptor, idItem);
	}
 	
 	public String match(String idReceptor, int idItemNecessario) {
 		return controllerEdoe.match(idReceptor, idItemNecessario);
 	}
 	
 	public String realizaDoacao(int idItemNecessario, int idItemDoado, String data) {
 		return controllerEdoe.realizaDoacao(idItemNecessario, idItemDoado, data);
 	}
 	
 	public String listaDoacoes() {
 		return controllerEdoe.listaDoacoes();
 	}
 	
 	public void finalizaSistema() throws IOException {
 		FileOutputStream arquivo = new FileOutputStream("sistema_serializado/objetos.ser");
 		ObjectOutputStream oos = new ObjectOutputStream(arquivo);
 		oos.writeObject(controllerEdoe);
 		oos.close();
 	}
 	
 	public void iniciaSistema() throws IOException, ClassNotFoundException {
 		FileInputStream arquivo = new FileInputStream("sistema_serializado/objetos.ser");
 		ObjectInputStream ois = new ObjectInputStream(arquivo);
 		controllerEdoe = (ControllerEdoe) ois.readObject();
 		ois.close();
 	}
 	
}