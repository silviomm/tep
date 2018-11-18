package bloomFilter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BloomFilterTest {

	private BloomFilter<String> bbf;

	@Before
	public void setUp() throws Exception {
		this.bbf = new BasicBloomFilter<>(100, 5);
	}

	@Test
	public void verificaTresPalavrasBasicBloomFilterTest() {
		this.bbf.add("oie");
		this.bbf.add("ola");

		assertTrue(this.bbf.mightContain("oie"));
		assertTrue(this.bbf.mightContain("ola"));
		assertFalse(this.bbf.mightContain("nao esta ai"));
	}

}
