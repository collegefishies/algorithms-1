import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnionFindTest {
    private QuickFindUF quickFindUF;
    private QuickUnionUF quickUnionUF;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private WeightedQuickUnionWithPathCompressionUF weightedQuickUnionWithPathCompressionUF;
    private int n = 100;
    private int m = 300;
    private int q = 1000;

    @BeforeEach
    public void setup() {
        quickFindUF = new QuickFindUF(n);
        quickUnionUF = new QuickUnionUF(n);
        weightedQuickUnionUF = new WeightedQuickUnionUF(n);
        weightedQuickUnionWithPathCompressionUF = new WeightedQuickUnionWithPathCompressionUF(n);
    }

    private void unionAll(int p, int q) {
        quickFindUF.union(p, q);
        quickUnionUF.union(p, q);
        weightedQuickUnionUF.union(p, q);
        weightedQuickUnionWithPathCompressionUF.union(p, q);
    }

    private void assertConnectedAll(int p, int q) {
        boolean expected = quickFindUF.connected(p, q);

        assertEquals(expected, quickUnionUF.connected(p, q), "QuickUnionUF failed the connected test for " + p + " and " + q);
        assertEquals(expected, weightedQuickUnionUF.connected(p, q), "WeightedQuickUnionUF failed the connected test for " + p + " and " + q);
        assertEquals(expected, weightedQuickUnionWithPathCompressionUF.connected(p, q), "WeightedQuickUnionWithPathCompressionUF failed the connected test for " + p + " and " + q);
    }

    @Test
    public void testUnionFindImplementations() {
        Random random = new Random(); // You can add a seed here like random = new Random(42);
        int iterations = 6;

        for (int iteration = 0; iteration < iterations; iteration++) {
            setup();

            for (int i = 0; i < m; i++) {
                int p = random.nextInt(n);
                int q = random.nextInt(n);

                unionAll(p, q);
            }

            for (int i = 0; i < q; i++) {
                int p = random.nextInt(n);
                int q = random.nextInt(n);

                assertConnectedAll(p, q);
            }
        }
    }
}
