import java.util.Scanner;

public class QuickFindUF {
    private int[] setOf;


    public QuickFindUF(int N) {
        //O(N)
        setOf = new int[N];
        for (int i = 0; i < N; i++) {
            setOf[i] = i;
        }
    }

    public void union(int p, int q) {
        //O(N)
        int set1 = setOf[p];
        int set2 = setOf[q];
        for (int i = 0; i < setOf.length; i++) {
            if (setOf[i] == set2) {
                setOf[i] = set1;
            }
        }
    }

    public boolean connected(int p, int q) {
        //O(1)
        return setOf[p] == setOf[q];
    }

    public static void main(String[] args) {
        //O(N^2)
        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter the number of elements:");
        if (!scanner.hasNextInt()) {
            System.out.println("No integer input provided. Exiting.");
            return;
        }
        int N = scanner.nextInt();
        QuickFindUF qf = new QuickFindUF(N);


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
