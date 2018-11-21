package projetop2;

public abstract class Usuario implements Comparable<Usuario>{
	private String nome;
	private String email;
	private String celular;
	private String classe;
	private String status;
	private int contador;
	private String id;
	
	public Usuario(String nome, String email, String celular, String classe, String status, String id) {
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
		contador+=1;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(Usuario o) {
		return this.contador - o.geContador(); 
	}

	@Override
	public abstract String toString();

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getCelular() {
		return celular;
	}

	public String getClasse() {
		return classe;
	}
	
	public String geStatus() {
		return status;
	}
	
	public int geContador() {
		return contador;
	}
	
	public String geId() {
		return id;
	}
	
	public String atualizaUsuario(String nome, String email, String celular) {
		
		if(nome != null && nome.length()!=0 )
			this.nome = nome;
		else if(email != null && email.length() != 0)
			this.email = email;
		else if(celular != null && celular.length() != 0)
			this.celular = celular;
		return this.toString();
	}
	
}
