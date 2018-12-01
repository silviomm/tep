package bloomFilter;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
		IBloomFilter<String> obf = new OptimizedBloomFilter<>(2, 0.002);

		obf.add("oie");
		obf.add("ola");

//		System.out.println(new DecimalFormat("#0.00000000").format(obf.getCurrentFalsePositiveRate()));
		assertTrue(obf.getCurrentFalsePositiveRate() <= 0.002);

		assertTrue(obf.mightContain("oie"));
		assertTrue(obf.mightContain("ola"));
	}

	@Test
	public void naoDeixaAdicionarSeForUltrapassarTaxaMaximaDeFalsosPositivosDesejadaTest() {
		IBloomFilter<String> obf = new OptimizedBloomFilter<>(1, 0);

		assertTrue(obf.getCurrentFalsePositiveRate() <= 0);

		assertTrue(obf.add("oie"));
		assertFalse(obf.add("ola"));
	}

	@Test
	public void criaOptmizedBloomFilterComDoisMilItensETaxaMaximaDeZeroPorcentoTest() {
		IBloomFilter<Long> obf = new OptimizedBloomFilter<>(2000, 0);

		System.out.println(obf.getK());

		for (int i = 0; i < 2_000; i++) {
			assertTrue(obf.add(Long.valueOf(i)));
		}

//		System.out.println(new DecimalFormat("#0.00000000").format(obf.getCurrentFalsePositiveRate()));
		assertTrue(obf.getCurrentFalsePositiveRate() <= 0);

		for (int i = 2_000; i < 10_000; i++) {
			assertFalse(obf.mightContain(Long.valueOf(i)));
		}
	}

	@Test
	public void criaOptmizedBloomFilterComDoisMilItensETaxaMaximaDeCinquentaPorcentoTest() {
		IBloomFilter<Long> obf = new OptimizedBloomFilter<>(2000, 0.5);
		Random random = new Random(1234);
		Set<Long> longs = new HashSet<>();

		for (int i = 0; i < 2_000; i++) {
			long aux = random.nextInt(10_000);
			assertTrue(obf.add(aux));
			longs.add(aux);
		}

//		System.out.println(new DecimalFormat("#0.00000000").format(obf.getCurrentFalsePositiveRate()));
		assertTrue(obf.getCurrentFalsePositiveRate() <= 0.5);

		int cont = 0;
		for (int i = 0; i < 10_000; i++) {
			if (obf.mightContain(Long.valueOf(i)) && !longs.contains(Long.valueOf(i)))
				cont++;
		}

		assertTrue(cont <= 5_000);
	}

	@Test
	public void verificaSeExisteAlgumFalsoNegativoOptmizedBloomFilterComDoisMilItensTest() {
		IBloomFilter<Long> obf = new OptimizedBloomFilter<>(2000, 0);

		for (int i = 0; i < 2_000; i++) {
			assertTrue(obf.add(Long.valueOf(i)));
		}

		for (int i = 0; i < 2_000; i++) {
			assertTrue(obf.mightContain(Long.valueOf(i)));
		}
	}

	@Test
	public void verificaSeExisteAlgumFalsoNegativoBasicBloomFilterComDoisMilItensTest() {
		IBloomFilter<Long> bbf = new BasicBloomFilter<>(5000, 3);

		for (int i = 0; i < 2_000; i++) {
			assertTrue(bbf.add(Long.valueOf(i)));
		}

		for (int i = 0; i < 2_000; i++) {
			assertTrue(bbf.mightContain(Long.valueOf(i)));
		}
	}

}
