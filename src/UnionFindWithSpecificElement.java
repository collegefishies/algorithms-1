import java.util.Scanner;

public class UnionFindWithSpecificElement {
    private int[] parentOf;
    private int[] treeDepthOf;
    private int[] maxElementOf;


    public UnionFindWithSpecificElement(int N) {
        //O(N)
        parentOf = new int[N];
        treeDepthOf = new int[N];
        maxElementOf = new int[N];

        for (int i = 0; i < N; i++) {
            parentOf[i] = i;
            maxElementOf[i] = i;
            treeDepthOf[i] = 0;
        }
    }

    public int find(int q) {
        return maxElementOf[rootOf(q)];
    }

    public void union(int p, int q) {
        //Worst case: O(lg N)
        int root1 = rootOf(p);
        int root2 = rootOf(q);
        int depthOfTree1 = treeDepthOf[root1];
        int depthOfTree2 = treeDepthOf[root2];
        int largestElement1 = maxElementOf[root1];
        int largestElement2 = maxElementOf[root2];
        int largestElement = Math.max(largestElement1, largestElement2);

        if (depthOfTree1 > depthOfTree2) {
            parentOf[root2] = root1;
            maxElementOf[root1] = largestElement;
        } else if (depthOfTree2 > depthOfTree1) {
            parentOf[root1] = root2;
            maxElementOf[root2] = largestElement;
        } else {
            parentOf[root1] = root2;
            treeDepthOf[root1] += 1;
            maxElementOf[root2] = largestElement;
        }

    }

    public int rootOf(int p) {
        //Worst Case: O(lg N)
        //Best Case: O(1)
        while (parentOf[p] != p) {
            parentOf[p] = parentOf[parentOf[p]]; //path compression
            p = parentOf[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        //Worst Case: O(lg N)
        return rootOf(p) == rootOf(q);
    }

    public static void main(String[] args) {
        //Worst Case: O(N lg N)
        Scanner scanner = new Scanner(System.in);


        System.out.println("Enter the number of elements:");
        if (!scanner.hasNextInt()) {
            System.out.println("No integer input provided. Exiting.");
            return;
        }
        int N = scanner.nextInt();
        UnionFindWithSpecificElement qf = new UnionFindWithSpecificElement(N);


        System.out.println("Enter union/find/connected commands:");
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
            } else if (command.equals("find")) {
                int p = scanner.nextInt();
                System.out.println("find(" + p + ") = " + qf.find(p));
            } else {
                System.out.println("Unknown command. Use 'union', 'find', or 'connected'.");
            }
        }

        scanner.close();
    }
}
