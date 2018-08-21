import org.junit.Before;

public class PercolationStatsTest {

    private PercolationStats stats;

    @Before
    public void setUp() throws Exception {
        stats = new PercolationStats(3, 10);
    }
}
