package trabalhos;

import java.util.Random;

public class DiceSimulator {

	public static void main(String[] args) {

		int n = 6;
		int numberOfSimulations = 1_000_000;
		float[] probs = new float[n];

		probs = createUniformDistribution(n);
		run(numberOfSimulations, n, probs);

		probs = aleatory(n);
		run(numberOfSimulations, n, probs);
	}

	// Cria array com distribuição uniforme
	public static float[] createUniformDistribution(int n) {
		float[] probs = new float[n];
		for (int i = 0; i < n; i++) {
			probs[i] = (float) 1 / n;
		}
		return probs;
	}

	/*
	 * Cria array com distribuição aleatória Ex do que pode ser gerado(com 6 lados):
	 * Contagem primeiro 'for': [36,4,3,7,0,10] Probabilidade gerada: [0.6, 0.066,
	 * 0.05, 0.1166, 0, 0.166] Onde: [0.6 + 0.066 + 0.05 + 0.1166 + 0 + 0.166 ~= 1]
	 * Como o nextInt() gera uniformemente, as probabilidades podem não variar tanto
	 * assim. O que se pode fazer é mexer no multFactor, que quanto maior, mais
	 * uniforme será a distribuição
	 */
	public static float[] aleatory(int n) {
		int multFactor = 5;
		float[] result = new float[n];

		int aux;
		for (int i = 0; i < n * multFactor; i++) {
			aux = randInRangeOf(n);
			result[aux]++;
		}

		for (int i = 0; i < n; i++) {
			result[i] = result[i] / (n * multFactor);
		}

		return result;
	}

	public static void run(int size, int n, float[] probs) {
		int a = 0, b = 0;
		for (int i = 0; i < size; i++) {
			a += threeInARow(n, probs);
			b += twoPairsInARow(n, probs);
		}
		printResult(a, b, size, probs);
	}

	private static void printResult(int three, int two, int size, float[] probs) {
		System.out.println("Number of simulations: " + size);

		System.out.print("Probability array: [" + probs[0]);
		for (int i = 1; i < probs.length; i++) {
			System.out.print(", ");
			System.out.print(probs[i]);
		}
		System.out.println("]");

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
		float rand = floatRand();
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

	public static int randInRangeOf(int n) {
		Random rand = new Random();
		return rand.nextInt(n);
	}

	public static float floatRand() {
		Random rand = new Random();
		return rand.nextFloat();
	}

}
