package bloomFilter;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class BloomFilterTest {

	private BloomFilter<String> bf; 
	
	@Before
	public void initialize() {
		this.bf = new BloomFilter<>(100, 5);
	}
	
	@Test
	void test() {
		bf.add("oie");
		bf.add("ola");
		
		System.out.println(bf.mightContain("oie"));
		System.out.println(bf.mightContain("gdagadgadg"));
		System.out.println(bf.mightContain("ola"));
	}

}
