public class Reversecustomername{
    static String reverseCustomerName(String name){
        String result = "";

        for(int i = name.length() - 1; i >= 0; i--)
            result += name.charAt(i);

        return result;
    }

    public static void main(String[] args){
        String name = "Sunil";

        System.out.println("Original Name: " + name);
        System.out.println("Reversed Name: " +
                           reverseCustomerName(name));
    }
}