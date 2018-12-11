package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item implements Comparable<Item>, Serializable{
	private int idItem;
	private int quantidade;
	private String descritor;
	private List<String> tags;
	
	public Item(int idItem, int quantidade, String descritor, List<String> tags) {
		this.idItem = idItem;
		this.quantidade = quantidade;
		this.descritor = descritor;
		this.tags = tags;
	}
	
	public String atualizaItem(String novasTags, int novaQuantidade) {
		if (novasTags != null ) {
			if(novasTags.length() > 0) {
				String[] vetorTags = novasTags.split(",");
				List<String> listaTags = new ArrayList<String>();
				
				//Adicionando as tags do vetor no ArrayList
				for (String c : vetorTags) {
					listaTags.add(c);
				}
				
				this.tags = listaTags;
			}
		}
		if (novaQuantidade > 0) {
			this.quantidade = novaQuantidade;
		}
		return this.toString();
	}

	public String retornaDescricaoEQuantidade() {
		return this.quantidade + " - " + descritor;
	}

	public String toStringCombo() {
		return this.idItem + " - " + this.getDescritor() + ", tags: " + this.tags.toString() + ", quantidade: " + this.quantidade + ", "; 
	}
	
	@Override
	public String toString() {
		if (tags.size() == 0) {
			return this.idItem + " - " +this.descritor.toString() + ", quantidade: " + this.quantidade;
		}
		return this.idItem + " - " + this.descritor.toString() + ", tags: " + this.tags.toString() + ", quantidade: " + this.quantidade;
	}
	
	public String getDescritor() {
		return descritor;
	}

	public void setQuantidade(int quantidade) {
		if(quantidade > 0)
			this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return quantidade;
	}
	
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

	@Override
	public int compareTo(Item o) {
		return this.descritor.compareTo(o.getDescritor());
	}
	
	public String idItemToString() {
		return this.idItem + " - " + this.descritor + ", " + "tags: " + this.tags.toString() + ", quantidade: " + this.quantidade;
	}

	public List<String> getTags() {
		return tags;
	}

}