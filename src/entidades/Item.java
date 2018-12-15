package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Representacao de um item que um usuario do sistema Edoe pode possuir
 * Todo item possui um id unico, quantidade, descritor (previamente adicionado no sistema) e tags que são usadas para descrever o item
 * @author debora leda
 *
 */
public class Item implements Comparable<Item>, Serializable{
	private int idItem;
	private int quantidade;
	private String descritor;
	private List<String> tags;
	
	/**
	 * Constroi um item a partir do seu id, quantidade, descritor e suas tags
	 * @param idItem identificador unico do item
	 * @param quantidade quantidade do item
	 * @param descritor descritor do item
	 * @param tags tags que descrevem o item
	 */
	public Item(int idItem, int quantidade, String descritor, List<String> tags) {
		if (descritor == null || descritor.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		if (quantidade <= 0)
			throw new IllegalArgumentException("Entrada invalida: quantidade deve ser maior que zero.");
	
		this.idItem = idItem;
		this.quantidade = quantidade;
		this.descritor = descritor.trim().toLowerCase();
		this.tags = tags;
	}
	
	/**
	 * Atualiza as tags e a quantidade de um item. Caso tags nao sejam passadas, apenas a quantidade
	 * sera atualizada. A nova quantidade deve ser sempre maior que 0
	 * @param novasTags novas tags do item
	 * @param novaQuantidade nova quantidade, deve ser maior que 0
	 * @return a representacao em string do item 
	 */
	public String atualizaItem(String novasTags, int novaQuantidade) {
		if (novasTags != null ) {
			if(novasTags.length() > 0) {
				String[] vetorTags = novasTags.split(",");
				List<String> listaTags = new ArrayList<String>();
				
				for (String c : vetorTags) {
					listaTags.add(c.trim());
				}
				this.tags = listaTags;
			}
			
		}
		if (novaQuantidade > 0) {
			this.quantidade = novaQuantidade;
		}
		return this.toString();
	}

	/**
	 * Funcao usada para retornar uma string contendo a quantidade e descricoa de um item
	 * no formato QUANTIDADE - DESCRICAO
	 * @return string contendo a quantidade e descricoa de um item
	 */
	public String retornaDescricaoEQuantidade() {
		return this.quantidade + " - " + descritor;
	}
	
	/**
	 * Representacao em string de um item, no formato
	 * caso o item nao possua tags: ID - DESCRITOR, quantidade: QUANTIDADE 
	 * caso o item possua tags: ID - DESCRITOR, tags: TAGS, quantidade: QUANTIDADE
	 */
	@Override
	public String toString() {
		if (tags == null || tags.size() == 0) {
			return this.idItem + " - " +this.descritor.toString() + ", quantidade: " + this.quantidade;
		}
		return this.idItem + " - " + this.descritor.toString() + ", tags: " + this.tags.toString() + ", quantidade: " + this.quantidade;
	}
	
	/**
	 * Retorna string contendo descritor 
	 * @return string contendo descritor do item
	 */
	public String getDescritor() {
		return descritor;
	}
	
	/**
	 * Retorna int contendo quantidade 
	 * @return int contendo quantidade do item
	 */
	public int getQuantidade() {
		return quantidade;
	}
	
	/**
	 * Retorna int contendo id 
	 * @return int contendo id do item
	 */
	public int getIdItem() {
		return idItem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descritor == null) ? 0 : descritor.hashCode());
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}

	/**
	 * Compara se dois itens sao iguais a partir do desccritor e das tags
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (descritor == null) {
			if (other.descritor != null)
				return false;
		} else if (!descritor.equals(other.descritor))
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}

	/**
	 * Compara descritores
	 */
	@Override
	public int compareTo(Item o) {
		return this.descritor.compareTo(o.getDescritor());
	}

	/**
	 * Retorna uma final list contendo as tags de um item 
	 * @return final list contendo as tags de um item 
	 */
	public final List<String> getTags() {
		return tags;
	}

}