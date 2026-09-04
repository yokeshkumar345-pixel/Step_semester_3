import java.util.*;

public class Wordfrequency
{
    static void printFilteredWordFrequency(String feedback)
    {
        feedback = feedback.toLowerCase();
        feedback = feedback.replace(".", "");
        feedback = feedback.replace(",", "");

        String[] words = feedback.split("\\s+");

        String[] stopWords =
            {"the", "was", "and", "a", "is", "of", "in"};

        HashMap<String, Integer> count =
            new HashMap<>();

        for(String word : words)
        {
            boolean stop = false;

            for(String s : stopWords)
            {
                if(word.equals(s))
                {
                    stop = true;
                    break;
                }
            }

            if(!stop)
                count.put(word,
                    count.getOrDefault(word, 0) + 1);
        }

        ArrayList<Map.Entry<String, Integer>> list =
            new ArrayList<>(count.entrySet());

        list.sort((a, b) ->
            b.getValue() - a.getValue());

        for(Map.Entry<String, Integer> e : list)
            System.out.println(
                e.getKey() + ": " + e.getValue());
    }

    public static void main(String[] args)
    {
        printFilteredWordFrequency(
            "The mentor was great, the session was great and clear.");
    }
}