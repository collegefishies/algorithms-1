import java.util.Scanner;

public class QuickUnionUF {
    private int[] parentOf;


    public QuickUnionUF(int N) {
        parentOf = new int[N];
        for (int i = 0; i < N; i++) {
            parentOf[i] = i;
        }
    }

    public void union(int p, int q) {
        int root1 = rootOf(p);
        int root2 = rootOf(q);
        parentOf[root2] = root1;
    }

    public int rootOf(int p) {
        while (parentOf[p] != p) {
            p = parentOf[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return rootOf(p) == rootOf(q);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter the number of elements:");
        if (!scanner.hasNextInt()) {
            System.out.println("No integer input provided. Exiting.");
            return;
        }
        int N = scanner.nextInt();
        QuickUnionUF qf = new QuickUnionUF(N);


        System.out.println("Enter union/find commands:");
        while (scanner.hasNext()) {
            String command = scanner.next();
            if (command.equals("union")) {
                int p = scanner.nextInt();
                int q = scanner.nextInt();
                qf.union(p, q);
            } else if (command.equals("connected")) {
                int p = scanner.nextInt();
                int q = scanner.nextInt();
                System.out.println("connected(" + p + ", " + q + ")? " + qf.connected(p, q));
            } else {
                System.out.println("Unknown command. Use 'union' or 'connected'.");
            }
        }

        scanner.close();
    }
}
