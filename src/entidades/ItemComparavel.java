package entidades;

import java.util.Comparator;

public class ItemComparavel implements Comparator<Item>{

	@Override
	public int compare(Item o1, Item o2) {
		if (o1.getQuantidade() > o2.getQuantidade())
			return -1;
		else if ((o1.getQuantidade() < o2.getQuantidade()))
			return 1;
		else 
			return 0;
	}
	
}
