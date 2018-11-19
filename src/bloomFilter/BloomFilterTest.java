package bloomFilter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BloomFilterTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void verificacaoBasicaBasicBloomFilterTest() {
		IBloomFilter<String> bbf = new BasicBloomFilter<>(100, 5);

		bbf.add("oie");
		bbf.add("ola");

		assertTrue(bbf.mightContain("oie"));
		assertTrue(bbf.mightContain("ola"));
	}

	@Test
	public void verificacaoBasicaOptimizedBloomFilterTest() {
		OptmizedBloomFilter<String> obf = new OptmizedBloomFilter<>(2, 0.002);

		obf.add("oie");
		obf.add("ola");

		assertTrue(obf.getCurrentFalsePositiveProbability() <= 0.002);

		assertTrue(obf.mightContain("oie"));
		assertTrue(obf.mightContain("ola"));
	}

	@Test
	public void naoDeixaAdicionarSeForUltrapassarProbabilidadeDeFalsosPositivosDesejadaTest() {
		OptmizedBloomFilter<String> obf = new OptmizedBloomFilter<>(1, 0);

		assertTrue(obf.getCurrentFalsePositiveProbability() <= 0);

		assertTrue(obf.add("oie"));
		assertFalse(obf.add("ola"));
		
		assertTrue(obf.getCurrentFalsePositiveProbability() <= 0);
	}

}
