import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] th; //thresholds
    private int trials;

    public PercolationStats(int n, int T) {
        Percolation percolation;
        trials = T;
        th = new double[trials];
        int[] siteToOpen;

        for (int i = 0; i < trials; i++) {

            percolation = new Percolation(n);
            //System.out.println("trial: " + (i+1));

            while(!percolation.percolates()){
                siteToOpen = getNewRandomSite(percolation, n);
                percolation.open(siteToOpen[0], siteToOpen[1]);
            }


            System.out.println("end trial: " + (i+1));
            System.out.println("open sites: " + (percolation.numberOfOpenSites()));
            System.out.println("");

            th[i] = (double)percolation.numberOfOpenSites()/(n*n);
        }
    }

    private int[] getNewRandomSite(Percolation percolation, int n){
        int randomCol;
        int randomRow;

        //System.out.println("open sites: " + (percolation.numberOfOpenSites()));

        do{
            randomRow = StdRandom.uniform(n) + 1;
            randomCol = StdRandom.uniform(n) + 1;
        }while(percolation.isOpen(randomRow, randomCol));

        return new int[]{randomRow, randomCol};
    }

    public double mean(){
        System.out.println("th " + (th[0]));
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

    public static void main(String[] args){

        int n, T;
        n = Integer.parseInt(args[0]);
        T = Integer.parseInt(args[1]);

        System.out.println("hola!");

        PercolationStats stats = new PercolationStats(n, T);

        System.out.println("mean                    = " + stats.mean());
        System.out.println("stdev                   = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() +", "+stats.confidenceHi()+"]");

    }
}
