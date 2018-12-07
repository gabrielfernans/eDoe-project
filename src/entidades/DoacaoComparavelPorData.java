package entidades;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class DoacaoComparavelPorData implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data1 = LocalDate.parse(o1.split(" - ")[0], formato);
		LocalDate data2 = LocalDate.parse(o2.split(" - ")[0], formato);
		if(data1.isAfter(data2))
			return 1;
		if(data1.isBefore(data2))
			return -1;
		return 0;
	}

}
