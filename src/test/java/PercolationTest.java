import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PercolationTest {

    private Percolation percolation;

    @Before
    public void setUp() throws Exception {
        percolation = new Percolation();
    }

    @Test
    public void testOpenSite() {
        assertEquals(false, percolation.isOpen(1,1) );
        percolation.open(1,1);
        assertEquals(true, percolation.isOpen(1,1) );
    }
}
