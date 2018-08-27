import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lifespan {
	public static void main(String[] args) {
		List<String> years = new ArrayList<>();

		// fill test
		years.add("102 118");
		years.add("100 130");
		years.add("80 120");
		years.add("101 103");
		years.add("90 102");
		
		alg1(years);
		alg2(years);
	}

	/*
	 * Algoritmo 1: Complexidade O(l*n + L) 
	 	* L := tamanho do range de anos (ano_max - ano_min) 
	 	* l := range máximo vivido por alguma pessoa 
	 	* n := uma lista com n pares (timestamp_nascimento, timestamp_morte)
	 */
	private static void alg1(List<String> years) {

		Map<Integer, Integer> count = new HashMap<Integer, Integer>();

		int numberOfPeople = 0;
		int mostLivedYear = 0;

		for (String bornAndDeath : years) {
			String born = bornAndDeath.split(" ")[0];
			String death = bornAndDeath.split(" ")[1];

			for (int i = Integer.parseInt(born); i <= Integer.parseInt(death); i++) {
				if (!count.containsKey(i)) {
					count.put(i, 0);
				}
				Integer val = count.get(i);
				val++;

				count.put(i, val);

				if (val > numberOfPeople) {
					numberOfPeople = val;
					mostLivedYear = i;
				}
			}
		}

		System.out.println(mostLivedYear);
	}

	/*
	 * Algoritmo 2: Complexidade O(n log n)
	 	* n := uma lista com n pares (timestamp_nascimento, timestamp_morte)
	 */
	private static void alg2(List<String> years) {
		List<String> sortedYears = new ArrayList<>();
		for (String bornAndDeath : years) {
			String born = bornAndDeath.split(" ")[0];
			int death = Integer.parseInt(bornAndDeath.split(" ")[1]);
			death++;
			sortedYears.add(born + " +");
			sortedYears.add(death + " -");
		}
		sortedYears.sort((a, b) -> {
			int year1 = Integer.parseInt(a.split(" ")[0]);
			int year2 = Integer.parseInt(b.split(" ")[0]);

			return year1 <= year2 ? -1 : 1;
		});

		int numberOfPeople = 0;
		int mostLivedYear = 0;
		int alive = 0;
		
		for(String date : sortedYears) {
			int year = Integer.parseInt(date.split(" ")[0]);
			String id = date.split(" ")[1];
			
			if(id.equals("+"))	alive++;
			else	alive--;
			
			if(alive > numberOfPeople) {
				numberOfPeople = alive;
				mostLivedYear = year;
			}
			
		}
		
		System.out.println(mostLivedYear);
	}
}