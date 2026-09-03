public class Speedaccuracycheacker{
    static void checkTypingAccuracy(String original, String typed){
        int matched = 0;
        int firstMismatch = -1;

        for(int i = 0; i < original.length(); i++){
            if(original.charAt(i) == typed.charAt(i))
                matched++;
            else if(firstMismatch == -1)
                firstMismatch = i;
        }

        double accuracy = (matched * 100.0) / original.length();

        if(firstMismatch == -1)
            System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | No Mismatches",
                    matched, original.length(), accuracy);
        else
            System.out.printf(
                "Matched: %d/%d | Accuracy: %.2f%% | First Mismatch at position %d",
                matched, original.length(), accuracy, firstMismatch + 1);
    }

    public static void main(String[] args){
        checkTypingAccuracy("hello world", "hello worlt");
    }
}