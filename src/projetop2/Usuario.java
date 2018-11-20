package projetop2;

public abstract class Usuario implements Comparable<Usuario>{
	private String nome;
	private String email;
	private String celular;
	private String classe;
	private String status;
	private int contador;
	
	public Usuario(String nome, String email, String celular, String classe, String status) {
		if(nome == null ||nome.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: nome nao pode ser vazio ou nulo.");
		if(email == null || email.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: email nao pode ser vazio ou nulo.");
		if(celular == null || celular.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: celular nao pode ser vazio ou nulo.");
		if(classe == null || classe.trim().equals(""))
			throw new IllegalArgumentException("Entrada invalida: classe nao pode ser vazia ou nula.");
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.status = status;
		contador+=1;
	}

	@Override
	public abstract int hashCode(); 

	@Override
	public abstract boolean equals(Object obj);
	
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
