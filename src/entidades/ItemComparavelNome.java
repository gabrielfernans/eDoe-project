package entidades;

import java.util.Comparator;

public class ItemComparavelNome implements Comparator<Item> {

	@Override
	public int compare(Item o1, Item o2) {
		return o1.getDescritor().compareTo(o2.getDescritor());
	}

}
