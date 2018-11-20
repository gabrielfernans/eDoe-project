package projetop2;

public abstract class Usuario {
	private String nome;
	private String email;
	private String celular;
	private String classe;
	private String status;
	
	public Usuario(String nome, String email, String celular, String classe, String status) {
		super();
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.status = status;
	}

	@Override
	public abstract int hashCode(); 

	@Override
	public abstract boolean equals(Object obj);
	
	

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
