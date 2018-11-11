package trabalhos;

import java.util.Random;

public class DiceSimulator {

	public static void main(String[] args) {

		int n = 6;
		int numberOfSimulations = 1_000_000;
		float[] probs = createArrayOfProbabilities(n);
		run(numberOfSimulations, n, probs);
	}

	public static void run(int size, int n, float[] probs) {
		int a = 0, b = 0;
		for (int i = 0; i < size; i++) {
			a += threeInARow(n, probs);
			b += twoPairsInARow(n, probs);
		}
		printResult(a, b, size);
	}

	private static void printResult(int three, int two, int size) {
		System.out.println("Number of simulations: " + size);
		System.out.println("Probability of 3 in a row: " + (float) three / size);
		System.out.println("Probability of 2 pairs in a row: " + (float) two / size);
		System.out.println("---------------");
	}

	private static int twoPairsInARow(int n, float[] probs) {
		int[] v = new int[4];
		for (int i = 0; i < 4; i++) {
			v[i] = roll(n, probs);
		}
		if (v[0] == v[1] && v[2] == v[3])
			return 1;
		return 0;
	}

	private static int threeInARow(int n, float[] probs) {
		int[] v = new int[3];
		for (int i = 0; i < 3; i++) {
			v[i] = roll(n, probs);
		}
		if (v[0] == v[1] && v[0] == v[2] && v[1] == v[2])
			return 1;
		return 0;
	}

	public static int roll(int n, float[] probs) {
		float rand = rand();
		float x = 0, y = 0;
		int result = -1;
		for (int i = 0; i < n; i++) {
			y += probs[i];
			if (x <= rand && rand < y) {
				result = i;
				break;
			}
			x = y;
		}
		return result;
	}

	public static float[] createArrayOfProbabilities(int n) {
		float[] probs = new float[n];
		for (int i = 0; i < n; i++) {
			probs[i] = (float) 1 / 6;
		}
		return probs;
	}

	public static float rand() {
		Random rand = new Random();
		return rand.nextFloat();
	}

}
