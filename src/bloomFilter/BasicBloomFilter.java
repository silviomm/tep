package bloomFilter;

public class BasicBloomFilter<T> implements BloomFilter<T>{

	private int m;
	private int k;
	private int[] filter;

	public BasicBloomFilter(int m, int k) {
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
	public void add(T elem) {
		int[] hashes = getHashes(elem);
		for (int h : hashes) {
			this.filter[h] = 1;
		}
	}

}
