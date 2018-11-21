package projetop2;

public class Receptor extends Usuario{

	// manipula a lista de itens a serem recebidos
	
	public Receptor(String id, String nome, String email, String celular, String classe, String status) {
		super(nome, email, celular, classe, status, id);
	}

	public String getCnpj() {
		return super.geId().substring(0,2) + "." + super.geId().substring(2,5) + "." + super.geId().substring(5,8) + "/" + super.geId().substring(8,12) + "-" +super.geId().substring(12, 14);
	}

	@Override
	public String toString() {
		return super.getNome() + "/" + getCnpj() + ", " + super.getEmail() + ", " + super.getCelular() + ", status: " + super.geStatus(); 
	}
	
	
}
