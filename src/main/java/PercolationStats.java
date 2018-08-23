import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] th; //thresholds
    private int trials;

    public PercolationStats(int n, int T) {
        Percolation percolation;
        th = new double[trials];
        trials = T;

        for (int i = 0; i < trials; i++) {

            percolation = new Percolation(n);

            while(!percolation.percolates()){
                openNewRandomSite(percolation, n);
            }

            th[i] = percolation.numberOfOpenSites()/(n*n);
        }
    }

    private void openNewRandomSite(Percolation percolation, int n){
        int randomCol;
        int randomRow;

        do{
            randomRow = StdRandom.uniform(n) + 1;
            randomCol = StdRandom.uniform(n) + 1;
        }while(!percolation.isOpen(randomRow, randomCol));

        percolation.open(randomRow, randomCol);
    }

    public double mean(){
        return StdStats.mean(th);
    }

    public double stddev(){
        return StdStats.stddev(th);
    }

    public double confidenceLo(){
        return mean() - ( 1.96*stddev() )/Math.sqrt(trials);
    }

    public double confidenceHi(){
        return mean() + ( 1.96*stddev() )/Math.sqrt(trials);
    }
}
