package bloomFilter;

public class BloomFilter<T> {

	private int m;
	private int k;
	private int[] filter;

	public static void main(String[] args) {
		BloomFilter<String> bf = new BloomFilter<>(100, 5);
		bf.add("oie");
		bf.add("ola");

		System.out.println(bf.mightContain("oie"));
		System.out.println(bf.mightContain("gdagadgadg"));
		System.out.println(bf.mightContain("ola"));
	}

	public BloomFilter(int m, int k) {
		this.m = m;
		this.k = k;
		this.filter = new int[m];
	}

	private int[] getHashes(T elem) {
		int[] result = new int[this.k];
		for (int i = 0; i < this.k; i++) {
			result[i] = Math.abs(elem.hashCode() * i) % this.m;
		}
		return result;
	}

	public boolean mightContain(T elem) {
		int[] hashes = getHashes(elem);
		for (int h : hashes) {
			if (this.filter[h] != 1)
				return false;
		}
		return true;
	}

	public void add(T elem) {
		int[] hashes = getHashes(elem);
		for (int h : hashes) {
			this.filter[h] = 1;
		}
	}

}
