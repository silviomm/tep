package bloomFilter;

public class BasicBloomFilter<T> implements IBloomFilter<T> {

	private int k;
	private int[] filter;
	private int n;

	public BasicBloomFilter(int m, int k) {
		this.k = k;
		this.filter = new int[m];
	}

	private int[] getHashes(T elem) {
		int[] result = new int[this.k];
		for (int i = 0; i < this.k; i++) {
			result[i] = Math.abs(elem.hashCode() * i) % this.filter.length;
		}
		return result;
	}

	@Override
	public double getCurrentFalsePositiveProbability() {
		return Math.pow((1 - Math.exp(-this.k * (double) this.n / (double) this.filter.length)), this.k);
	}

	@Override
	public boolean mightContain(T elem) {
		int[] hashes = getHashes(elem);
		for (int h : hashes) {
			if (this.filter[h] != 1)
				return false;
		}
		return true;
	}

	@Override
	public boolean add(T elem) {
		int[] hashes = getHashes(elem);
		for (int h : hashes) {
			this.filter[h] = 1;
		}
		return true;
	}

}
