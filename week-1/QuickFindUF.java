import java.util.Scanner;

public class QuickFindUF {
    private int[] id;


    public QuickFindUF(int N){
        id = new int[N];
        for(int i = 0; i < N; i++) {id[i] = i;}
    }

    public void union(int p, int q){
        int set1 = id[p];
        int set2 = id[q];
        for(int i = 0; i < id.length; i++) {
            if (id[i] == set2) {
                id[i] = set1;
            }
        }
    }

    public boolean connected(int p, int q){
        return id[p] == id[q];
    }

    public static void main(String[] args) {
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
            } else if (command.equals("connected")){
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
