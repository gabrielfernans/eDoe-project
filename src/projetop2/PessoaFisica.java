package projetop2;

public class PessoaFisica extends Usuario{

	private final String cpf;

	public PessoaFisica(String id, String nome, String email, String celular, String classe, String status) {
		super(nome, email, celular, classe, status);
		if(id == null || id.trim().equals("")) 
			throw new IllegalArgumentException("Entrada invalida: id do usuario nao pode ser vazio ou nulo.");
		this.cpf = id;
	}

	public String getCpf() {
		return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11) ;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		PessoaFisica other = (PessoaFisica) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return super.getNome() + "/" + getCpf() + ", " + super.getEmail() + ", " + super.getCelular() + ", status: " + super.geStatus(); 
	}

	
}
