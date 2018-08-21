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
        doOpen(rowIndex-1, colIndex-1);
    }

    private void doOpen(int rowIndex, int colIndex){
        checkBoundaries(rowIndex, colIndex);

        if(checkOpen(rowIndex, colIndex)){
            return;
        }

        siteStates[rowIndex*N + colIndex] = true;
        countOpenSites++;

        connectToOpenedNeighbors(rowIndex, colIndex);
    }

    private void connectToOpenedNeighbors(int rowIndex, int colIndex) {
        connectToTopSite(rowIndex, colIndex);
        connectToLeftSite(rowIndex, colIndex);
        connectToRightSite(rowIndex, colIndex);
        connectToBottomSite(rowIndex, colIndex);
    }

    private void connectToTopSite(int rowIndex, int colIndex) {
        if(rowIndex == 0){
            wqu.union(rowIndex*N + colIndex, virtualTopSiteIndex);
        }else if( checkOpen(rowIndex-1, colIndex) ){
            wqu.union(rowIndex*N + colIndex, (rowIndex-1)*N + colIndex);
        }
    }

    private void connectToLeftSite(int rowIndex, int colIndex) {
        if( siteExists(rowIndex, colIndex - 1) && checkOpen(rowIndex, colIndex - 1) ){
            wqu.union(rowIndex*N + colIndex, rowIndex*N + colIndex - 1);
        }
    }

    private void connectToRightSite(int rowIndex, int colIndex) {
        if( siteExists(rowIndex, colIndex + 1) && checkOpen(rowIndex, colIndex + 1) ){
            wqu.union(rowIndex*N + colIndex, rowIndex*N + colIndex + 1);
        }
    }

    private void connectToBottomSite(int rowIndex, int colIndex) {
        if(rowIndex == N-1){
            wqu.union(rowIndex*N + colIndex, virtualBottomSiteIndex);
        }else if( checkOpen(rowIndex+1, colIndex) ){
            wqu.union(rowIndex*N + colIndex, (rowIndex+1)*N + colIndex);
        }
    }

    private boolean siteExists(int rowIndex, int colIndex){
        return rowIndex >= 0 && colIndex >= 0 && rowIndex < N && colIndex < N;
    }

    public boolean isOpen(int rowIndex, int colIndex) {
        return checkOpen(rowIndex-1, colIndex-1);
    }

    private boolean checkOpen(int rowIndex, int colIndex) {
        checkBoundaries(rowIndex, colIndex);
        return siteStates[rowIndex*N + colIndex];
    }

    public boolean isFull(int rowIndex, int colIndex) {
        return checkFull(rowIndex-1, colIndex-1);
    }

    private boolean checkFull(int rowIndex, int colIndex) {
        checkBoundaries(rowIndex, colIndex);
        return wqu.connected(rowIndex*N + colIndex, virtualTopSiteIndex);
    }

    public boolean percolates() {
        return wqu.connected(virtualTopSiteIndex, virtualBottomSiteIndex);
    }

    public int numberOfOpenSites() {
        return countOpenSites;
    }

    private void checkBoundaries(int rowIndex, int colIndex){
        if( !siteExists(rowIndex, colIndex) ) {
            throw new IllegalArgumentException();
        }
    }

}
