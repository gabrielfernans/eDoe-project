package projetop2;

import java.util.ArrayList;
import java.util.List;

public class Receptor extends Usuario{

	private List<Item> itensNecessarios = new ArrayList<>();
	
	public Receptor(String id, String nome, String email, String celular, String classe, String status, int cont) {
		super(nome, email, celular, classe, status, id, cont);
	}
	
	@Override
	public void adicionaItem(Item item) {
		this.itensNecessarios.add(item);
	}

	public String getId() {
		return super.formataId();
	}

	@Override
	public String toString() {
		return super.getNome() + "/" + this.getId() + ", " + super.getEmail() + ", " + super.getCelular() + ", status: " + super.geStatus(); 
	}
}
