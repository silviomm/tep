package trabalhos;

import java.util.Random;

public class CoinSimulator {

	public static void main(String[] args) {

		run(1);
		System.out.println("---------------------");
		run(10);
		System.out.println("---------------------");
		run(50);
		System.out.println("---------------------");
		run(66);
		System.out.println("---------------------");
		run(100);

	}

	public static void run(int prob) {
		int size = 50;
		int twoConsucutiveHeads = 0, fiveHeads = 0;
		for (int i = 0; i < size; i++) {
			int[] result = sim(prob);
			fiveHeads += result[0];
			twoConsucutiveHeads += result[1];
		}
		print(size, prob, fiveHeads, twoConsucutiveHeads);
	}

	public static void print(int size, int prob, int fiveHeads, int twoConsucutiveHeads) {
		System.out.println("Probabilidade(cara): " + prob + "%");
		System.out.println("Número de simulações: " + size);
		System.out.println("Média para sair 5 caras: " + ((float) fiveHeads / size));
		System.out.println("Média para sair 2 caras consecutivas: " + ((float) twoConsucutiveHeads / size));
	}

	public static int[] sim(int prob) {
		int[] result = new int[2];
		int heads = 0, lastCoin = -1;
		int flag5Heads = -1, flag2ConsecutiveHeads = -1;
		for (int i = 1; i < 10_000; i++) {
			int coin = flip(prob);
			if (coin == 0) {
				heads++;
			}
			if (heads == 5 && flag5Heads == -1) {
				flag5Heads = 0;
				result[0] = i;
			}
			if (coin == 0 && lastCoin == 0 && flag2ConsecutiveHeads == -1) {
				flag2ConsecutiveHeads = 0;
				result[1] = i;
			}
			if (flag2ConsecutiveHeads != -1 && flag5Heads != -1)
				break;
			lastCoin = coin;
		}

		return result;
	}

	public static int flip(int headsProb) {
		if (rand() % 100 < headsProb)
			return 0;
		return 1;
	}

	public static int rand() {
		Random rand = new Random();
		return rand.nextInt(100_001);
	}
}
