package bloomFilter;

public interface BloomFilter<T> {

	public boolean mightContain(T elem);
	public void add(T elem);
	
}
