package entidades;

import java.util.Comparator;

public class ItemComparavelPorId implements Comparator<Item> {

	@Override
	public int compare(Item o1, Item o2) {
		if (o1.getIdItem() < o2.getIdItem()) {
	          return -1;
	     }
	     else if (o1.getIdItem() > o2.getIdItem()) {
	          return 1;
	     }
	     return 0;
	}

}