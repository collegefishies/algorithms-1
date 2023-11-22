private class WeightedQuickUnionUF {
    private int[] parentOf;
    private int[] depthOf;
    private int islands;

    public WeightedQuickUnionUF(int n){
        parentOf = new int[n];
        depthOf = new int[n];
        islands = n;

        for (int i = 0; i < n; i ++) {
            parentOf[i] = i;
            depthOf[i] = 0;
        }
    }

    public void union(int p, int q) {
        int rootA = find(p); int rootB = find(q);
        int depthA = depthOf[rootA]; int depthB = depthOf[rootB];
        if (connected(p, q)) {
            return;
        }


        if (depthA > depthB) {
            parentOf[rootB] = rootA;
        } else if (depthB > depthA) {
            parentOf[rootA] = rootB;
        } else {
            parentOf[rootB] = rootA;
            depthOf[rootB] += 1;
        }

        islands -= 1;
    }
    public int find(int p) { // returns root of p.
        if (p < 0) {
            throw new IllegalArgumentException("p must be positive not " + p);
        }

        while (parentOf[p] != p) {
            //path compression;
            parentOf[p] = parentOf[parentOf[p]];
            p = parentOf[p];
        }

        return p;
    }
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    public int sets() { return islands; }
}

private class Solution {
    int m; int n;

    private int enumerate(int i, int j) { return i + j*m; }
    private boolean isValid(int i, int j) { return i >= 0 && i < m && j >= 0 && j < n;}

    public int numIslands(char[][] grid) {
        int m = grid.length; int n = grid[0].length; this.m = m; this.n = n;
        System.out.println("Size was " + m*n);
        int waterIslands = 0;

        WeightedQuickUnionUF unionFind = new WeightedQuickUnionUF(m * n);
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0, -1}};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    waterIslands += 1;
                    continue;
                }

                for (int[] d: directions) {
                    int x = i + d[0]; int y = j + d[1];
                    if (!isValid(x,y)) {
                        continue;
                    }

                    if (grid[x][y] == '1' && grid[i][j] == '1'){
                        unionFind.union(enumerate(i,j), enumerate(x, y));
                    }
                }
            }
        }


        return unionFind.sets() - waterIslands;
    }
}
