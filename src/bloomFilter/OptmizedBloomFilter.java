package bloomFilter;

public class OptmizedBloomFilter<T> implements IBloomFilter<T> {

	private int k;
	private int n;
	private double maxProb;
	private int[] filter;

	public OptmizedBloomFilter(int expectedElements, double maxProb) {
		this.maxProb = maxProb;
		int[] result = this.getOptimumParameters(expectedElements, maxProb);
		this.filter = new int[result[0]];
		this.k = result[1];
	}

	private int[] getOptimumParameters(int n, double desiredProbability) {
		int filterSize = 1;
		int k = getOptimumK(filterSize, n);
		while (getFalsePositiveProbability(k, n, filterSize) > desiredProbability) {
			filterSize *= 2;
			k = getOptimumK(filterSize, n);
		}
		return new int[] { filterSize, k };
	}

	private int getOptimumK(int filterSize, int n) {
		return (int) Math.ceil((filterSize / n) * Math.log(2));
	}

	private double getFalsePositiveProbability(int k, int n, int filterSize) {
		return Math.pow((1 - Math.exp(-k * (double) n / (double) filterSize)), k);
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
		return this.getFalsePositiveProbability(this.k, this.n, this.filter.length);
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
		if(canAddAnotherElement()) {			
			int[] hashes = getHashes(elem);
			for (int h : hashes) {
				this.filter[h] = 1;
			}
			this.n++;
			return true;
		}
		return false;
	}

	private boolean canAddAnotherElement() {
		return this.getFalsePositiveProbability(k, n+1, this.filter.length) <= this.maxProb;
	}

}
