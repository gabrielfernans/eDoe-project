package controllers;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import entidades.Item;
import entidades.ItemComparavelPorId;
import entidades.Usuario;

/**
 * Classe que representa o controlador dos usuarios cadastrados no sistema.
 * @author deb
 *
 */
public class ControllerUsuario {
	private Map<String, Usuario> usuarios = new HashMap<String, Usuario>();
	private int contadorOrdemUsuario = 0;
	private int idItem = 1;
	
	/**
	 * Adiciona um usuario doador ao sistema
	 * @param id id do usuario (cpf para pessoa fisica, cnpj para outro)
	 * @param nome nome do usuario
	 * @param email email do usuario
	 * @param celular celular do usuario
	 * @param classe classe do usuario (pessoa fisica ou outro)
	 * @return String contendo o id do usuario
	 */
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {	
		if(id == null || id.trim().equals("")) 
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		
		if(usuarios.containsKey(id))
			throw new IllegalArgumentException("Usuario ja existente: " + id + ".");
		
		if(classe == null || classe.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: classe nao pode ser vazia ou nula.");
		
		switch(classe) {
		
		case "PESSOA_FISICA": 
		case "ONG": 
		case "IGREJA": 
		case "ORGAO_PUBLICO_MUNICIPAL": 
		case "ORGAO_PUBLICO_FEDERAL": 
		case "ORGAO_PUBLICO_ESTADUAL":
		case "ASSOCIACAO": case "SOCIEDADE":
			usuarios.put(id, new Usuario(id, nome, email, celular, classe, "doador", contadorOrdemUsuario++));
			break;
			
		default:
			throw new IllegalArgumentException("Entrada invalida: opcao de classe invalida.");
		}
		return id;
	}
	
	/**
	 * Pesquisa por usuario a partir do seu identificador
	 * @param id id do usuario (cpf para pessoa fisica, cnpj para outro)
	 * @return representacao do usuario que possui o id informado
	 */
	public String pesquisaUsuarioPorId(String id) {
		if(id == null || id.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		
		if(!usuarios.containsKey(id))
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		
		return usuarios.get(id).toString();
	}
	
	/**
	 * Pesquisa por usuario a partir do seu nome. Caso o usuario nao exista sera lancada excecao
	 * @param nome nome do usuario
	 * @return representacao do usuario que possui o nome informado
	 */
	public String pesquisaUsuarioPorNome(String nome) {
		if(nome == null || nome.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		
		List<Usuario> users = new ArrayList<Usuario>();
		boolean userNaoExiste = true;
		
		for(Usuario user : usuarios.values()) {
			if(user.getNome().equals(nome)) {
				users.add(user);
				userNaoExiste = false;
			}
		}
		if (userNaoExiste)
			throw new IllegalArgumentException("Usuario nao encontrado: " + nome + ".");
		
		Collections.sort(users);
		return editaLista(users);
	}

	/**
	 * Atualiza informacoes de um usuario a partir do seu id, podendo atualizar apenas nome, email e celular
	 * @param id id do usuario
	 * @param nome nome do usuario
	 * @param email email do usuario
	 * @param celular celular do usuario
	 * @return String contendo os novos dados do usuario
	 */
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		
		if(id == null || id.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		
		if(!usuarios.containsKey(id))
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		
		return usuarios.get(id).atualizaUsuario(nome, email, celular);
	}

	/**
	 * remove o usuario do sistema a partir do seu id
	 * @param id id do usuario
	 */
	public void removeUsuario(String id) {
		if(id == null || id.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		
		if(!usuarios.containsKey(id))
			throw new IllegalArgumentException("Usuario nao encontrado: " + id + ".");
		
		usuarios.remove(id);
	}

	/**
	 * Adiciona usuario receptores ao sistema a partir de um arquivo .csv
	 * @param caminho caminho informando a localizacao do arquivo
	 * @throws IOException excecao informando campos invalidos
	 */
	public void lerReceptores(String caminho) throws IOException {
		Scanner sc = new Scanner(new File(caminho));
		String linha = null;
		
		while(sc.hasNextLine()) {
			linha = sc.nextLine();
			
			if(linha.equals("id,nome,E-mail,celular,classe"))
				continue;
			String[] dadosReceptor = linha.split(",");
			
			if(dadosReceptor.length != 5) {
				sc.close();
				throw new IOException("Campos invalidos");
			}
				
			if(caminho.split("/")[1].equals("atualizaReceptores.csv"))
				usuarios.get(dadosReceptor[0]).atualizaUsuario(dadosReceptor[1], dadosReceptor[2], dadosReceptor[3]);
			
			else if(caminho.split("/")[1].equals("novosReceptores.csv"))
				usuarios.put(dadosReceptor[0], new Usuario(dadosReceptor[0],dadosReceptor[1], dadosReceptor[2], dadosReceptor[3], dadosReceptor[4], "receptor", contadorOrdemUsuario++));
		}
		sc.close();
	}

	/**
	 * Cadastra item que o usuario possui(pode ser para doar ou para receber) 
	 * @param idUsuario id do usuario
	 * @param descritor descritor do item, String
	 * @param quantidade quantidade do item, String
	 * @param tags tags do item, String
	 * @return retorna o idDoItem
	 */
	public int cadastraItem(String idUsuario, String descritor, int quantidade, String tags) {
		
		if (descritor == null || descritor.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		
		if (quantidade <= 0)
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
		
		if (idUsuario == null || idUsuario.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		
		if (!this.usuarios.containsKey(idUsuario))
			throw new IllegalArgumentException("Usuario nao encontrado: " + idUsuario + ".");
		
		return this.usuarios.get(idUsuario).cadastraItem(this.idItem++, descritor.trim().toLowerCase(), quantidade, tags);
	}

	/**
	 * Exibe um item que esteja inserido no sistema a partir do seu id e do id do usuario que o possui
	 * @param idItem id do item
	 * @param idDoador id do usuario
	 * @return String contendo dados do item buscado
	 */
	public String exibeItem(int idItem, String idDoador) {
		if (!this.usuarios.containsKey(idDoador))
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
			
		return this.usuarios.get(idDoador).exibeItem(idItem);
	}
	
	/**
	 * Retorna listagem de todos os itens que um usuário receptor necessita, 
	 * no formato: ID DO ITEM - DESCRITOR DO ITEM, tags: [tag1, tag2...], quantidade: QUANTIDADE DE ITENS, Receptor: NOME RECEOTOR/ID | 
	 * ID DO ITEM - DESCRITOR DO ITEM, tags: [tag1, tag2...], quantidade: QUANTIDADE DE ITENS, Receptor: NOME RECEOTOR/ID | 
	 * @return String contendo dados do item e do usuario
	 */
	public String listaItensNecessarios() {
		Map<Item, String> mapItensEusers = new HashMap<>();
		List<Item> itensLista = new ArrayList<>();
		for(Usuario user: usuarios.values()){
			if(user.getStatus().equals("receptor")){
				for(Item item: user.getItens().values()){
					mapItensEusers.put(item, user.getNome() + "/" + user.getId());
					itensLista.add(item);
				}
			}
		}
		
		Collections.sort(itensLista, new ItemComparavelPorId());
		String aux ="";
		for(Item item: itensLista){
			aux += item.toString() + ", Receptor: " + mapItensEusers.get(item) + " | ";
		}
		
		return aux.substring(0, aux.length()-3);	
	}

	/**
	 * Atualiza a quantidade de itens e as tags inseridas, se uma tag 
	 * @param idItem
	 * @param idDoador
	 * @param quantidade
	 * @param tags
	 */
	public String atualizaItem(int idItem, String idDoador, int quantidade, String tags) {
		if(idItem < 0)
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		
		if (idDoador == null || idDoador.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		
		if (!this.usuarios.containsKey(idDoador))
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		
		return usuarios.get(idDoador).atualizaItem(idItem, tags, quantidade);
	}
	
	/**
	 * Remove um determinado item do sistema a partir do id.
	 * @param idItem Id do item a ser retirado.
 	 * @param idDoador Documento de identificacao do doador.
	 */
	public void removeItem(int idItem, String idDoador) {
		if(idItem < 0)
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		
		if (idDoador == null || idDoador.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		
		if (!this.usuarios.containsKey(idDoador))
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		
		usuarios.get(idDoador).removeItem(idItem);
	}
	
	private String editaLista(List<Usuario> listaDeUsuario) {
		String users = "";
		for(Usuario u : listaDeUsuario) {
			users += u.toString() + " | ";
		}
		return users.substring(0, users.length()-3);
	}
	
	//erick
	public Map<String, Usuario> getUsuarios() {
		return usuarios;
	}
	
	
	/**
	 * Metodo que lista todos os itens relacionados a uma dada string de pesquisa.
	 * A listagem ocorre em ordem alfabetica considerando os descritores dos itens. 
	 * @param desc Parametro designado pelo usuario para string de pesquisa.
	 * @param map Um mapa com os usuarios que sera necessario para acessar as listas de itens dos usuarios.
	 * @return O retorno eh uma string com a representacao do id do item, a descricao, tag e quantidade.
	 */
	public String pesquisaItemParaDoacaoPorDescricao(String desc) {
		if (desc == null || desc.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
		
		List<Item> listDeItens= new ArrayList<Item>();
		
		for (Usuario usuario : usuarios.values()) {
			for (Item item : usuario.getItens().values()) {
				if (item.getDescritor().contains(desc) && !listDeItens.contains(item))
					listDeItens.add(item);
			}
		}
		Collections.sort(listDeItens);
		
		String toString = "";
		for (Item itens : listDeItens) {
				toString += itens.idItemToString() + " | ";
		}
		return toString.substring(0, toString.length()-3);
	}
	
	
	
}