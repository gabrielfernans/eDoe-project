package projetop2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Item {
	private int id;
	private int quantidade;
	private Descricao descricao;
	private String data;
	private List<String> tags = new ArrayList<>();
	
	public Item(int id, int quantidade, Descricao descricao, String data, List<String> tags) {
		this.id = id;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.data = data;
		this.tags = tags;
	}
	
	
}
