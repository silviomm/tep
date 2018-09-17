package tep.trabalhos;

import java.util.HashSet;
import java.util.Set;

public class CombinacaoNK {

	public static Set<Set<Integer>> sets = new HashSet<>();

	public static void main(String[] args) {

		int n = 10;
		int k = 1;

		backtrack(new HashSet<>(), n, k);
	}

	public static void backtrack(Set<Integer> currentState, int n, int k) {

		// check if we got a k-size combination
		if (currentState.size() == k) {
			// check if we already printed that combination
			if (!sets.contains(currentState)) {
				// print combination and add to the ones printed
				currentState.forEach((e) -> System.out.print(e + " "));
				System.out.println("");
				sets.add(new HashSet<>(currentState));
			}
			return;
		}

		// for all candidates
		for (int i = 1; i <= n; i++) {

			// check the validity
			if (currentState.contains(i))
				continue;

			// take a step
			currentState.add(i);

			// call recursively
			backtrack(currentState, n, k);

			// take a step back
			currentState.remove(i);
		}
	}

}
