package trabalhos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Anagrama {

	public static Set<Set<Integer>> sets = new HashSet<>();

	public static void main(String[] args) {

		String word = "arara";

		backtrack(new ArrayList<>(), word, 0, createWordMap(word), false, ' ');
	}

	private static Map<Character, Integer> createWordMap(String word) {
		Map<Character, Integer> qntd = new HashMap<Character, Integer>();

		for (int i = 0; i < word.length(); i++) {
			char letter = word.charAt(i);
			Integer q = qntd.get(letter);
			if (q == null)
				qntd.put(letter, 1);
			else
				qntd.put(letter, q + 1);
		}

		return qntd;
	}

	public static void backtrack(List<Character> currentState, String word, int consonantsTogether,
			Map<Character, Integer> qntd, boolean pHppn, char lastLetter) {

		// check if we got a full anagram
		if (currentState.size() == word.length()) {
			System.out.println(currentState);
		} else {
			// for all candidates
			for (int i = 0; i < word.length(); i++) {
				
				boolean flagConsonants = false;
				boolean flagP = false;
				int flagVowel = 0;

				// get the letter
				char letter = word.charAt(i);

				int q = qntd.get(letter);
				// used all resources for that letter
				if (q == 0)
					continue;

				boolean isVowel = checkIfIsVowel(letter);

				// check the validity

				// starts with vowel
				if (currentState.size() == 0 && !isVowel)
					continue;

				// no equal letters together
				if (letter == lastLetter)
					continue;

				if (!isVowel) {

					// consonants not more than 3 together
					if (consonantsTogether + 1 > 2) {
						continue;
					}

					consonantsTogether++;
					flagConsonants = true;

					// First P appearance must be before first G appearance, if exists
					if (letter == 'g' && qntd.containsKey('p') && !pHppn) {
						continue;
					}
					if (letter == 'p' && !pHppn) {
						flagP = true;
						pHppn = true;
					}
				} else {
					flagVowel = consonantsTogether;
					consonantsTogether = 0;
				}

				// take a step
				currentState.add(letter);
				qntd.put(letter, q - 1);

				// call recursively
				backtrack(currentState, word, consonantsTogether, qntd, pHppn, letter);

				// take a step back
				currentState.remove(currentState.size() - 1);
				qntd.put(letter, q);

				// flags of change:
				if (flagConsonants) {
					flagConsonants = false;
					consonantsTogether--;
				}
				if (flagP) {
					flagP = false;
					pHppn = false;
				}
				if (flagVowel != 0) {
					consonantsTogether = flagVowel;
				}
			}
		}
	}

	private static boolean checkIfIsVowel(char charAt) {
		if (charAt == 'a' || charAt == 'e' || charAt == 'i' || charAt == 'o' || charAt == 'u')
			return true;
		return false;
	}

}
