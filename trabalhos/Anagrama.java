package tep.trabalhos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Anagrama {

	public static Set<Set<Integer>> sets = new HashSet<>();

	public static void main(String[] args) {

		String word = "bpag";

		backtrack(new ArrayList<>(), word, 0, false, ' ', createWordMap(word));
	}

	private static Map<Character, Integer> createWordMap(String word) {
		Map<Character, Integer> qntd = new HashMap<Character, Integer>();
		
		for (int i = 0; i < word.length(); i++) {
			char letter = word.charAt(i);
			Integer q = qntd.get(letter);
			if(q == null)
				qntd.put(letter, 1);
			else
				qntd.put(letter, q + 1);
		}
		
		return qntd;
	}

	public static void backtrack(List<Character> currentState, String word, int consonantsTogether, boolean hppn,
			char lastLetter, Map<Character, Integer> qntd) {

		// check if we got a full anagram
		if (currentState.size() == word.length()) {
			System.out.println(currentState);
		}
		else {
			// for all candidates
			for (int i = 0; i < word.length(); i++) {

				// get the letter
				char letter = word.charAt(i);

				// check the validity
				if (checkIfIsVowel(letter)) {
					if (i == 0 && lastLetter == ' ') {
						continue;
					}
					consonantsTogether = 0;
				} else {
					consonantsTogether++;
					if (letter == 'p')
						hppn = true;
					if (letter == 'g' && hppn == false)
						continue;
				}
				if (letter == lastLetter)
					continue;

				int q = qntd.get(letter);
				if (q == 0)
					continue;
				else
					qntd.put(letter, q - 1);

				// take a step
				currentState.add(letter);

				// call recursively
				backtrack(currentState, word, consonantsTogether, hppn, letter, qntd);

				// take a step back
				currentState.remove(currentState.size() - 1);
				qntd.put(letter, q);
			}
		}
	}

	private static boolean checkIfIsVowel(char charAt) {
		if (charAt == 'a' || charAt == 'e' || charAt == 'i' || charAt == 'o' || charAt == 'u')
			return true;
		return false;
	}

}
