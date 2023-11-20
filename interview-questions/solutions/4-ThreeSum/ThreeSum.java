public class ThreeSum {
    public ThreeSum(int[] a) {
        int count = 0;
        System.out.println("Three Sum Count: " + count);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Integers are inputted as a list.");
            return;
        }

        int[] a = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            a[i] = Integer.parseInt(args[i]);
        }

        new ThreeSum(a);
    }
}
