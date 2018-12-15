package entidades;

import java.util.Comparator;

public class ItemComparavel implements Comparator<Item>{

	@Override
	public int compare(Item o1, Item o2) {
		
		int flag = o2.getQuantidade() - o1.getQuantidade();
		
		if(flag == 0) 
			flag = o1.getDescritor().compareTo(o2.getDescritor());
		
		return flag;
	}

}    
