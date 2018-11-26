package entidades;

import java.util.List;

public class Item{
	private int id;
	private int quantidade;
	private String descritor;
	private List<String> tags;
	
	public Item(int id, int quantidade, String descritor, List<String> tags) {
		this.id = id;
		this.quantidade = quantidade;
		this.descritor = descritor;
		this.tags = tags;
	}
	
	public void atualizaItem(List<String> novasTags, int novaQuantidade) {
		this.tags = novasTags;
		this.quantidade = novaQuantidade;
		
	}
	
	@Override
	public String toString() {
		if (tags.size() == 0) {
			return this.id + " - " +this.descritor.toString() + ", quantidade: " + this.quantidade;
		}
		return this.id + " - " + this.descritor.toString() + ", tags:" + this.tags.toString() + ", quantidade: " + this.quantidade;
	}
	
	public String retornaDescricaoItemEQuantidade() {
		return this.quantidade + " - " + descritor.toString();
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descritor;
	}

	public void setDescricao(String descricao) {
		this.descritor = descricao;
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
		result = prime * result + ((descritor == null) ? 0 : descritor.hashCode());
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
		if (descritor == null) {
			if (other.descritor != null)
				return false;
		} else if (!descritor.equals(other.descritor))
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
