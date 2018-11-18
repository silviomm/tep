package bloomFilter;

public interface IBloomFilter<T> {

	public boolean mightContain(T elem);
	public void add(T elem);
	
}
