package projetop2;

public abstract class Usuario implements Comparable<Usuario>{
	private String nome;
	private String email;
	private String celular;
	private String classe;
	private String status;
	private int contador;
	private String id;
	
	public Usuario(String nome, String email, String celular, String classe, String status, String id, int cont) {
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
		this.contador = cont;
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
		return this.geContador() - o.geContador(); 
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
	
	public String formataId() {
		//08.704.413/0002-40
		if(getClasse().equals("PESSOA_FISICA"))
			return id.substring(0, 3) + "." + id.substring(3, 6) + "." + id.substring(6, 9) + "-" + id.substring(9, 11);
		return id.substring(0, 2) + "." + id.substring(2, 5) + "." + id.substring(5, 8) + "/" + id.substring(8, 12) + "-" + id.substring(12, 14);	
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
	
	public String atualizaReceptor(String nome, String email, String celular) {
		if(!nome.equals(this.nome))
			this.nome = nome;
		if(!email.equals(this.email))
			this.email = email;
		if(!celular.equals(this.celular))
			this.celular = celular;
		return this.toString();
	}
}
