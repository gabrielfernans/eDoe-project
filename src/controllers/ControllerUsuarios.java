package controllers;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import entidades.Item;
import entidades.ItemComparavel;
import entidades.ItemComparavelPorId;
import entidades.ItemComparavelPorPontuacao;
import entidades.Usuario;

/**
 * Classe que representa o controlador dos usuarios cadastrados no sistema.
 * @author deb
 *
 */
public class ControllerUsuarios implements Serializable {
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
		if(idItem < 0)
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		if (idDoador == null || idDoador.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		if (!this.usuarios.containsKey(idDoador))
			throw new IllegalArgumentException("Usuario nao encontrado: " + idDoador + ".");
		return this.usuarios.get(idDoador).exibeItem(idItem);
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
	
	private <T> String editaLista(List<T> lista) {
		String users = "";
		for( T u : lista) {
			users += u.toString() + " | ";
		}
		return users.substring(0, users.length()-3);
	}
	
	/**
	 * Metodo que lista todos os itens relacionados a uma dada string de pesquisa.
	 * A listagem ocorre em ordem alfabetica considerando os descritores dos itens. 
	 * @param desc Parametro designado pelo usuario para string de pesquisa.
	 * @return O retorno eh uma string com a representacao do id do item, a descricao, tag e quantidade.
	 */
	public String pesquisaItemParaDoacaoPorDescricao(String desc) {
		if (desc == null || desc.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
		
		List<Item> todosOsItens = this.todosOsItensDoSistema();
		List<Item> listDeItens= new ArrayList<Item>();
		
		for(Item item : todosOsItens) {
			if (item.getDescritor().contains(desc) && !listDeItens.contains(item))
				listDeItens.add(item);
		}
		Collections.sort(listDeItens);
		return this.editaLista(listDeItens);
	}
	
	/**
	 * Retorna item encontrado no sistema atraves do id
	 * @param idItem id do item que sera buscado no sistema
	 * @return Item encontrado, caso nao seja encontrado, lançara uma excessao
	 */
	private Item getItemPeloId(int idItem) {
		if(idItem < 0 )
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		for(Usuario user: usuarios.values()){
			if(user.getItens().containsKey(idItem))
					return user.getItens().get(idItem);
		}
		throw new IllegalArgumentException("Item nao encontrado: " + idItem + "."); 
	}
	
	/**
	 * Compara se as tags de dois itens sao iguais. Caso sejam iguais e na mesma ordem, o item ganha 10 pontos. Caso sejam iguais mas em ordem diferente, 
	 * o item ganha 5 pontos.
	 * @param tags tags de um item para doacao
	 * @param tagsNecessarias tags de um item necessario
	 * @return pontos que o item para a doacao acumulou
	 */
	private int comparadorDeTags(List<String> tags, List<String> tagsNecessarias) {
		int pontos = 20;
		for (int i = 0; i < tags.size(); i++) {	
			for (int j = 0; j < tagsNecessarias.size(); j++) {
				if (tags.get(i).equals(tagsNecessarias.get(j))) {
					if (i == j)
						pontos += 10;
					else 
						pontos += 5;
				}
			}
		}
		return pontos;
	}
	
	private List<Item> todosOsItensDoSistema(){
		List<Item> itensLista = new ArrayList<>();
		for(Usuario user: usuarios.values()){
			for(Item item: user.getItens().values()){
				itensLista.add(item);
			}
		}
		return itensLista;
	}
	
	/**
	 * Retorna listagem de todos os itens que um usuário receptor necessita, 
	 * no formato: ID DO ITEM - DESCRITOR DO ITEM, tags: [tag1, tag2...], quantidade: QUANTIDADE DE ITENS, Receptor: NOME RECEOTOR/ID | 
	 * ID DO ITEM - DESCRITOR DO ITEM, tags: [tag1, tag2...], quantidade: QUANTIDADE DE ITENS, Receptor: NOME RECEOTOR/ID | 
	 * @return String contendo dados do item e do usuario
	 */
	public String listaItensNecessarios() {
		Map<Item, Usuario> itensEUsuarios = new HashMap<Item, Usuario>();
		List<Item> itensLista = new ArrayList<Item>();
		for(Usuario user: usuarios.values()){
			for(Item item: user.getItens().values()){
				if(user.getStatus().equals("receptor")) {
					itensEUsuarios.put(item, user);
					itensLista.add(item);
				}
			}
		}
		
		Collections.sort(itensLista, new ItemComparavelPorId());
		String aux ="";
		for(Item item: itensLista){
				aux += item.toString() + ", Receptor: " + itensEUsuarios.get(item).getNome() + "/" + itensEUsuarios.get(item).getId() + " | ";
		}
		
		return aux.substring(0, aux.length()-3);	
	}
	
	/**
	 * Ordena matchs de acordo com a pontuacao que cada match possui
	 * @param mapItemPontos map que associa um item ao numero de pontos que ele possui
	 * @return string com os itens ordenados de acordo com os pontos
	 */
	private String ordenaMatchs(Map<String, Integer> mapItemPontos) {
		List<Integer> pontosOrdenados = new ArrayList<Integer>(); 

		pontosOrdenados.addAll(mapItemPontos.values());
		pontosOrdenados.sort(new ItemComparavelPorPontuacao());
		List<String> listaParaDoacaoOrdenada = new ArrayList<String>();		
		String itensParaDoacao = "";

		for(int ponto : pontosOrdenados) {
			for(String itemParaDoacao : mapItemPontos.keySet()) {
				if(mapItemPontos.get(itemParaDoacao) == ponto && !listaParaDoacaoOrdenada.contains(itemParaDoacao)) {
					listaParaDoacaoOrdenada.add(itemParaDoacao);
					itensParaDoacao += itemParaDoacao + " | ";
				}
			}
		}
		return itensParaDoacao.substring(0, itensParaDoacao.length()-3);
	}
	
	/**
	 * Realiza match no sistema de um item necessario com um item disponivel no sistema atraves do id do receptor e do id do item necessario
	 * @param idReceptor id do receptor
	 * @param idItemNecessario id do item necessario
	 * @return String ordenada dos matchs que o sistema realizou
	 */
	public String match(String idReceptor, int idItemNecessario) {
		if(idReceptor == null || idReceptor.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		if(this.pesquisaUsuarioPorId(idReceptor).split("status: ")[1].equals("doador"))
			throw new IllegalArgumentException("O Usuario deve ser um receptor: " + idReceptor + ".");
		if(idItemNecessario < 0) 
			throw new IllegalArgumentException("Entrada invalida: id do item nao pode ser negativo.");
		
		Item itemNecessario = this.getItemPeloId(idItemNecessario);
		HashMap<String, Integer> mapItemPontos = new HashMap<String, Integer>(); 
		boolean itemExiste = false;
		
		for(Usuario user: usuarios.values()){
			for(Item item: user.getItens().values()){
				if (user.getStatus().equals("doador")) {
					if (itemNecessario.getDescritor().equals(item.getDescritor())) {
						mapItemPontos.put(item.toString() + ", doador: " + user.getNome() + "/" + user.getId(), comparadorDeTags(item.getTags(), itemNecessario.getTags()));
						itemExiste = true;
					}
							
				}
			}
		}
		if(!itemExiste)
			return "";
		
		return this.ordenaMatchs(mapItemPontos);
	}
	
	
	private String getDonoDoItem(int idItem) {
		for(Usuario user: usuarios.values()){
			for(Item item: user.getItens().values()){
				if (idItem == item.getIdItem())
					return user.getNome() + "/" + user.getId();
			}
		}
		
		throw new IllegalArgumentException("Item nao encontrado: " + idItem + "."); 
	}
	
	public String realizaDoacao(int idItemNecessario, int idItemDoado, String data) {
		if(data == null || data.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: data nao pode ser vazia ou nula.");
		Item itemNecessario = this.getItemPeloId(idItemNecessario);
		Item itemDoado = this.getItemPeloId(idItemDoado);
		if(!itemNecessario.getDescritor().equals(itemDoado.getDescritor()))
			throw new IllegalArgumentException("Os itens nao tem descricoes iguais.");
		
		int quantidade = itemNecessario.getQuantidade();
		String[] infoDoacao = {this.getDonoDoItem(idItemDoado), itemNecessario.getDescritor(), "" + quantidade, this.getDonoDoItem(idItemNecessario)};
		
		if(itemDoado.getQuantidade() > itemNecessario.getQuantidade()) {
			itemDoado.atualizaItem("", itemDoado.getQuantidade() - itemNecessario.getQuantidade());
			this.removeItem(idItemNecessario, this.getDonoDoItem(idItemNecessario).split("/")[1]);
		}else if(itemDoado.getQuantidade() < itemNecessario.getQuantidade()){
			quantidade = itemDoado.getQuantidade();
			itemNecessario.atualizaItem("", itemNecessario.getQuantidade() - itemDoado.getQuantidade());
			this.removeItem(idItemDoado, this.getDonoDoItem(idItemDoado).split("/")[1]);
		}else{
			this.removeItem(idItemDoado, this.getDonoDoItem(idItemDoado).split("/")[1]);
			this.removeItem(idItemNecessario, this.getDonoDoItem(idItemNecessario).split("/")[1]);
		}
		infoDoacao[2] = "" + quantidade;
		String doacao = data + " - " + "doador: " + infoDoacao[0] + ", item: " + infoDoacao[1] + ", quantidade: " +
				infoDoacao[2] + ", receptor: " + infoDoacao[3];
		return doacao;
	}
	

	
	
	/**
	 * Metodo que lista todos os itens inseridos no sistema ordenada pela quantidade do item no sistema.
	 * Itens com a mesma quantidade serao ordenados pela ordem alfabetica de descricao.
	 * @param usuarios Um mapa com os usuarios que sera necessario para acessar as listas de itens dos usuarios.
	 * @return O retorno eh uma string com a representacao do id do item, a descricao, tag, quantidade e o doador.
	 */	
	public String listaItensParaDoacao() {
		ArrayList<Item> itensOrdenados = new ArrayList<Item>();
		Map<Item, Usuario> ItensEusers = new HashMap<Item, Usuario>();
		
		for (Usuario usuario : usuarios.values()) { 
			for (Item itens : (usuario.getItens().values())) {
				if(usuario.getStatus().equals("doador")) {
					itensOrdenados.add(itens);
					ItensEusers.put(itens, usuario);
				}
			}
		}
		
		Collections.sort(itensOrdenados, new ItemComparavel());
		String itensParaDoacao = "";
		for(Item i : itensOrdenados) {
			itensParaDoacao += i.toString()+ ", doador: " + ItensEusers.get(i).getNome() + "/" + ItensEusers.get(i).getId() + " | ";
		}
		
		return itensParaDoacao.substring(0, itensParaDoacao.length()-3);
	}
	
	/**
	 * Metodo que lista os descritores de itens cadastrados no sistema, em ordem alfabetica pela descricao do mesmo.
	 * Na saida, eh exibido a quantidade do item e sua descricao.
	 * @param usuarios Um mapa com os usuarios que sera necessario para acessar as listas de itens dos usuarios.
	 * @return O retorno eh uma string com a representacao textual dos itens
	 */
	public String listaDescritorDeItensParaDoacao(Set<String> descritores) {
		Map<String, Integer> itens = new HashMap<String, Integer>();
		for (Item item : this.todosOsItensDoSistema()) {
				itens.put(item.getDescritor(), item.getQuantidade());
		}
		for (String descricao : descritores) {
			if(!itens.containsKey(descricao))
				itens.put(descricao, 0);
		}
		List<String> itensOrdenados = new ArrayList<String>();
		itensOrdenados.addAll(itens.keySet());
		Collections.sort(itensOrdenados);
		
		String listaFinal = "";
		for(String descricao : itensOrdenados) {
			listaFinal += itens.get(descricao) + " - " + descricao + " | ";
		}
		return listaFinal.substring(0, listaFinal.length()-3);
	}
	
}