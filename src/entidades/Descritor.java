package entidades;

public class Descritor {
	
	private String descritor;

	public Descritor(String descritor) {
		this.descritor = descritor.trim().toLowerCase();
	}

	public int compareTo(Descritor descricao2) {
		return this.descritor.compareTo(descricao2.getDescricao());
	}

	public String getDescricao() {
		return descritor;
	}

	public void setDescricao(String descricao) {
		this.descritor = descricao;
	}

	@Override
	public String toString() {
		return descritor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descritor == null) ? 0 : descritor.hashCode());
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
		Descritor other = (Descritor) obj;
		if (descritor == null) {
			if (other.descritor != null)
				return false;
		} else if (!descritor.equals(other.descritor))
			return false;
		return true;
	}
	
}
