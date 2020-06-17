
/**
 * The Percolation class models a percolation system.
 *
 * @author Bowen Li
 */

package UnionFind;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int count = 0;                    // number of sites that have been opened
    private final int size;                   // width of percolation grid
    private boolean[][] sites;                // matrix of percolation grid
    private final int virtualTopSite;         // helper disjoint set structures with-
    private final int virtualButtomSite;      // 1D index of the grid cells;
    private final WeightedQuickUnionUF uf_A;  // used to determine percolation which only has top virtual site
    private final WeightedQuickUnionUF uf_B;  // used to determine percolation which has two virtual sites top one and the bottom one

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        size = n;
        virtualTopSite = n * n;
        virtualButtomSite = virtualTopSite + 1;
        uf_A = new WeightedQuickUnionUF(n * n + 1);
        uf_B = new WeightedQuickUnionUF(n * n + 2);
        sites = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sites[i][j] = false;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!sites[row-1][col-1]) {
            sites[row-1][col-1] = true;
            count++;
            if (row == 1) union(virtualTopSite, to2D(row, col));
            if (row == size) uf_B.union(virtualButtomSite, to2D(row, col));
            setNeighbors(row, col);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return sites[row-1][col-1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int index = uf_A.find(to2D(row, col));
        return isOpen(row, col) && index == uf_A.find(virtualTopSite);
    }

    private void validate(int p, int q) {
        if (p <= 0 || p > size || q <= 0 || q > size) {
            throw new IllegalArgumentException();
        }
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf_B.find(virtualTopSite) == uf_B.find(virtualButtomSite);
    }

    // UF.union
    private void union(int p, int q) {
        if (uf_A.find(p) != uf_A.find(q)) {
            uf_A.union(p, q);
            uf_B.union(p, q);
        }
    }

    // get index
    private int to2D(int row, int col) {
        return size * (row - 1) + col - 1;
    }

    // union possible neighbors
    private void setNeighbors(int row, int col) {
        if (row > 1 && isOpen(row - 1, col))
            union(to2D(row, col), to2D(row - 1, col));
        if (row < size && isOpen(row + 1, col))
            union(to2D(row, col), to2D(row + 1, col));
        if (col > 1 && isOpen(row, col - 1))
            union(to2D(row, col), to2D(row, col - 1));
        if (col < size && isOpen(row, col + 1))
            union(to2D(row, col), to2D(row, col + 1));
    }

}
