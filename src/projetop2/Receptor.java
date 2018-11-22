package projetop2;

public class Receptor extends Usuario{

	// manipula a lista de itens a serem recebidos
	
	public Receptor(String id, String nome, String email, String celular, String classe, String status, int cont) {
		super(nome, email, celular, classe, status, id, cont);
	}
	

	public String getId() {
		return super.formataId();
	}

	@Override
	public String toString() {
		return super.getNome() + "/" + this.getId() + ", " + super.getEmail() + ", " + super.getCelular() + ", status: " + super.geStatus(); 
	}
}
