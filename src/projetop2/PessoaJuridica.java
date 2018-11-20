package projetop2;

public class PessoaJuridica extends Usuario{

	private final String cnpj;
	
	public PessoaJuridica(String id, String nome, String email, String celular, String classe, String status) {
		super(nome, email, celular, classe, status);
		this.cnpj = id;
	}

	public String getCnpj() {
		return cnpj.substring(0,2) + "." + cnpj.substring(2,5) + "." + cnpj.substring(5,8) + "/" + cnpj.substring(8,12) + "-" +cnpj.substring(12, 14);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
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
		PessoaJuridica other = (PessoaJuridica) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.getNome() + "/" + getCnpj() + ", " + super.getEmail() + ", " + super.getCelular() + ", status: " + super.geStatus(); 
	}
	
	
}
