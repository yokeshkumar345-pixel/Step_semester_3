public class Warehouseinvator{
    static void analyzeInventory(int[] a, int[] b){
        int sumA = 0;
        int sumB = 0;

        int max = a[0];
        String section = "A";
        int index = 0;

        for(int i = 0; i < a.length; i++){
            sumA += a[i];

            if(a[i] > max)
            {
                max = a[i];
                section = "A";
                index = i;
            }
        }

        for(int i = 0; i < b.length; i++){
            sumB += b[i];

            if(b[i] > max)
            {
                max = b[i];
                section = "B";
                index = i;
            }
        }

        String status;

        if(sumA == sumB)
            status = "Balanced";
        else
            status = "Not Balanced";

        System.out.println("Section A Total: " + sumA);
        System.out.println("Section B Total: " + sumB);
        System.out.println("Status: " + status);
        System.out.println("Highest Quantity: " + max +
                           " (Section " + section +
                           ", Item " + (index + 1) + ")");
    }

    public static void main(String[] args){
        int[] a = {20, 15, 30};
        int[] b = {25, 10, 30};

        analyzeInventory(a, b);
    }
}