import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.Scanner;

public class SocialNetworkConnectivity {
    private WeightedQuickUnionUF unionFind;
    private int disjointSets;
    private Scanner scanner;

    public SocialNetworkConnectivity(Scanner scanner) {
        this.scanner = scanner;
        int n = scanner.nextInt(); // Read the number of members from the file
        unionFind = new WeightedQuickUnionUF(n);
        disjointSets = n;
    }

    private boolean connected(int p, int q) {
        return unionFind.find(p) == unionFind.find(q);
    }

    private void processFriendship() {
        while (disjointSets != 1 && scanner.hasNextLine()) {
            int p = scanner.nextInt();
            int q = scanner.nextInt();
            String timeStamp = scanner.next();
            System.out.println("" + p + ", " + q + ": " + timeStamp);

            if (!connected(p, q)) {
                unionFind.union(p, q);
                disjointSets--;
                if (disjointSets == 1) {
                    System.out.println("All members are connected at: " + timeStamp);
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SocialNetworkConnectivity connectivity = new SocialNetworkConnectivity(scanner);
        System.out.println("Enter friendship pairs followed by timestamp (e.g., 1 2 2023-11-14):");
        connectivity.processFriendship();
    }
}
