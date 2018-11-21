package projetop2;

public class Doador extends Usuario{
	
	// manipula a lista de itens a serem doados

	public Doador(String id, String nome, String email, String celular, String classe, String status) {
		super(nome, email, celular, classe, status, id);
	}

	public String getCpf() {
		return super.geId().substring(0, 3) + "." + super.geId().substring(3, 6) + "." + super.geId().substring(6, 9) + "-" + super.geId().substring(9, 11) ;
	}
	
	@Override
	public String toString() {
		return super.getNome() + "/" + getCpf() + ", " + super.getEmail() + ", " + super.getCelular() + ", status: " + super.geStatus(); 
	}

	
}
