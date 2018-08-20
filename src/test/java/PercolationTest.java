import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PercolationTest {

    private Percolation percolation;

    @Before
    public void setUp() throws Exception {
        percolation = new Percolation(3);
    }

    @Test
    public void testOpenSite() {
        assertEquals(false, percolation.isOpen(1,1) );
        percolation.open(1,1);
        assertEquals(true, percolation.isOpen(1,1) );
    }

    @Test
    public void testNumberOfOpenSites() {
        assertEquals(0, percolation.numberOfOpenSites() );
        percolation.open(1,1);
        assertEquals(1, percolation.numberOfOpenSites() );
    }

    @Test
    public void testIsFull() {
        assertEquals(false, percolation.isFull(1,1) );
        percolation.open(1,1);
        assertEquals(false, percolation.isFull(1,1) );

        assertEquals(false, percolation.isFull(0,1) );
        percolation.open(0,1);
        assertEquals(true, percolation.isFull(0,1) );
    }
}
