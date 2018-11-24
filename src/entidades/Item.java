package entidades;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Item implements Comparator<Item>{
	private int id;
	private int quantidade;
	private Descritor descricao;
	private String data;
	private List<String> tags;
	
	public Item(int id, int quantidade, Descritor descricao, String data, List<String> tags) {
		this.id = id;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.data = data;
		this.tags = new ArrayList<>();
	}
	
	public void atualizaItem(List<String> novasTags, int novaQuantidade) {
		this.tags = novasTags;
		this.quantidade = novaQuantidade;
		
	}
	
	@Override
	public String toString() {
		if (tags.size() == 0) {
			return this.id + " - " +this.descricao.toString() + ", quantidade: " + this.quantidade;
		}
		return this.id + " - " + this.descricao.toString() + ", tags:" + this.tags.toString() + ", quantidade: " + this.quantidade;
	}
	
	@Override
	public int compare(Item item1, Item item2) {
		return item1.getDescricao().compareTo(item2.getDescricao());
	}
	
	public String retornaDescricaoItemEQuantidade() {
		return this.quantidade + " - " + descricao.toString();
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Descritor getDescricao() {
		return descricao;
	}

	public void setDescricao(Descritor descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + id;
		result = prime * result + ((tags == null) ? 0 : tags.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id != other.id)
			return false;
		if (tags == null) {
			if (other.tags != null)
				return false;
		} else if (!tags.equals(other.tags))
			return false;
		return true;
	}

}
