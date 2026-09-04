public class Wordreversal{
    static String reverseEachWord(String sentence){
        String[] words = sentence.split(" ");
        String result = "";

        for(int i = 0; i < words.length; i++)
        {
            StringBuilder sb =
                new StringBuilder(words[i]);

            sb.reverse();

            result += sb;

            if(i < words.length - 1)
                result += " ";
        }

        return result;
    }

    public static void main(String[] args){
        System.out.println(
            reverseEachWord("hello club"));
    }
}
