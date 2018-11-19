package bloomFilter;

public interface IBloomFilter<T> {

	public boolean mightContain(T elem);

	public boolean add(T elem);

	public double getCurrentFalsePositiveProbability();

}
