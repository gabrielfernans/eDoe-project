package entidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Entidade usuario, representa um usuario do sistema, que pode ser receptor ou doador.
 * Cada usuario possui um nome, email, celular, classe (que informa se ele eh uma pessoa fisica ou algum orgao),
 * status (informado se eh doador ou receptor), uma colecao de itens e um id unico no sistema(cpf para pessoa fisica, cnpj para outro)
 * @author debora leda
 */

public class Usuario implements Comparable<Usuario>{
	
	private String nome;
	private String email;
	private String celular;
	private String classe;
	private String status;
	private int contadorOrdem;
	private String id;
	private Map<Integer, Item> itens = new HashMap<>();
	
	/**
	 * Construtor de usuario, a partir dos dados do mesmo
	 * @param id cpf para pessoa fisica, cnpj para pessoa juridica
	 * @param nome nome do usuario
	 * @param email email do usuario
	 * @param celular celular do usuario
	 * @param classe classe do usuario, informando se eh pessoa fisica ou outro orgao
	 * @param status status do usuario, indormando se eh doador ou receptor
	 * @param contadorOrdem contador usado depois para ordenar o usuario por ordem de insersao no sistema
	 */
	public Usuario(String id, String nome, String email, String celular, String classe, String status, int contadorOrdem) {
		if(nome == null ||nome.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		
		if(email == null || email.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: email nao pode ser vazio ou nulo.");
		
		if(celular == null || celular.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: celular nao pode ser vazio ou nulo.");
		
		if(classe == null || classe.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: classe nao pode ser vazia ou nula.");
		
		if(id == null || id.trim().equals("")) 
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.status = status;
		this.id = id;
		this.contadorOrdem = contadorOrdem;
	}
	
	/**
	 * Get todos os itens de um usuario
	 * @return um Map<Integer, Item> contendo todos os itens de um usuario
	 */
	public Map<Integer, Item> getItens() {
		return itens;
	}

	/**
	 * Atualiza informacoes de um usuario, podendo atualizar apenas nome, email e celular
	 * @param nome nome do usuario
	 * @param email email do usuario
	 * @param celular celular do usuario
	 * @return String contendo os novos dados do usuario
	 */
	public String atualizaUsuario(String nome, String email, String celular) {
		if(nome != null && nome.length()!=0 && !nome.equals(this.nome))
			this.nome = nome;
		if(email != null && email.length() != 0 && !email.equals(this.email))
			this.email = email;
		if(celular != null && celular.length() != 0 && !celular.equals(this.celular))
			this.celular = celular;
		return this.toString();
	}
	
	/**
	 * Cadastra item que o usuario possui(pode ser para doar ou para receber) 
	 * @param idItem identificador (gerado pelo sistema) do item , inteiro
	 * @param descritor descritor do item, String
	 * @param quantidade quantidade do item, String
	 * @param tags tags do item, String
	 * @return retorna o idDoItem
	 */
	public int cadastraItem(int idItem, String descritor, int quantidade, String tags) {
		if (descritor == null || descritor.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		if (quantidade <= 0)
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
		
		String[] vetorTags = tags.split(",");
		List<String> listaTags = new ArrayList<String>();
		
		//Adicionando as tags do vetor no ArrayList
		for (String c : vetorTags) {
			listaTags.add(c);
		}
		
		int idItemProvisorio = idItem;
		for (Item c : itens.values()) {
			if(c.equals(new Item(idItem, quantidade, descritor, listaTags))) {
				c.setQuantidade(quantidade);
				idItemProvisorio = c.getIdItem();
			}
		}
		if(itens.containsValue(new Item(idItemProvisorio, quantidade, descritor, listaTags))) {
			return idItemProvisorio;
		}
		
		this.itens.put(idItem, new Item(idItem, quantidade, descritor, listaTags));
		
		return idItem;
	}
	
	/**
	 * Exibe item a partir do seu id (inteiro)
	 * @param idItem id do item
	 * @return representacao em string do item
	 */
	public String exibeItem(int idItem) {
		if (!this.itens.containsKey(idItem))
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		
		return this.itens.get(idItem).toString();
	}
	
	/**
	 * Atualiza item, a partir do seu id. Os campos que podem ser atualizados sao as tags e a quantidade do item
	 * @param idItem id do item, int
	 * @param novasTags novasTags do item, String
	 * @param novaQuantidade novaQuantidade do item, int
	 */
	public String atualizaItem(int idItem, String novasTags, int novaQuantidade) {
		if (!this.itens.containsKey(idItem))
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");

		return this.itens.get(idItem).atualizaItem(novasTags, novaQuantidade);
	}
	
	/**
	 * Remove item do sistema a partir do seu id
	 * @param idItem id do item, int
	 */
	public void removeItem(int idItem) {
		if (this.itens.size() == 00 ) {
			throw new IllegalArgumentException("O Usuario nao possui itens cadastrados.");
		}
		if (!this.itens.containsKey(idItem)) {
			throw new IllegalArgumentException("Item nao encontrado: " + idItem + ".");
		}
		this.itens.remove(idItem);
	}

	/**
	 * Compara cada usuario pelo ordem de insercao no sistema
	 */
	@Override
	public int compareTo(Usuario o) {
		return this.getContador() - o.getContador(); 
	}

	/**
	 * Retorna uma String com nome do usuario
	 * @return nome do usuario
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Retorna uma String com email do usuario
	 * @return email do usuario
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Retorna uma String com celular do usuario
	 * @return celular do usuario
	 */
	public String getCelular() {
		return this.celular;
	}

	/**
	 * Retorna uma String com a classe do usuario
	 * @return classe do usuario
	 */
	public String getClasse() {
		return this.classe;
	}
	
	/**
	 * Retorna uma String com o id do usuario
	 * @return id do usuario
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * Retorna uma String com o status do usuario
	 * @return status do usuario
	 */
	public String getStatus() {
		return this.status;
	}
	
	/**
	 * Retorna uma String com o contador do usuario, aquele que eh usado para ordena-lo
	 * @return contador do usuario
	 */
	public int getContador() {
		return this.contadorOrdem;
	}
	
	/**
	 * Representacao em string do usuario
	 * No formato:
	 * 	NOME/ID, EMAIL, CELULAR, status: STATUS
	 */
	public String toString() {
		return getNome() + "/" + this.getId() + ", " + getEmail() + ", " + getCelular() + ", status: " + getStatus(); 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	/**
	 * Verifica se dois usuarios sao iguais a partir do seu id
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}