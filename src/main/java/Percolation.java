import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF wqu;
    private int N;
    private boolean[] siteStates;
    private int countOpenSites;
    private int virtualTopSiteIndex;
    private int virtualBottomSiteIndex;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        N = n;
        int squareN = N*N;
        virtualTopSiteIndex = squareN;
        virtualBottomSiteIndex = squareN + 1;

        wqu = new WeightedQuickUnionUF(squareN + 2);
        siteStates = new boolean[squareN];
        countOpenSites = 0;

        for (int i = 0; i < squareN; i++) {
            siteStates[i] = false;
        }
    }

    public void open(int rowIndex, int colIndex) {
        checkBoundaries(rowIndex, colIndex);

        siteStates[rowIndex*N + colIndex] = true;
        countOpenSites++;
    }

    public boolean isOpen(int rowIndex, int colIndex) {
        checkBoundaries(rowIndex, colIndex);

        return siteStates[rowIndex*N + colIndex];
    }

    public boolean isFull(int rowIndex, int colIndex) {
        checkBoundaries(rowIndex, colIndex);

        return false;
    }

    public int numberOfOpenSites() {
        return countOpenSites;
    }

    private void checkBoundaries(int rowIndex, int colIndex){
        if(rowIndex < 0 || colIndex < 0 || rowIndex >= N || colIndex >= N) {
            throw new IndexOutOfBoundsException();
        }
    }

}
