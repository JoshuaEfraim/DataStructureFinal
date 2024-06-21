import java.util.HashMap;
import java.util.Map;

public class FindSimilarityHashMap {

    public static HashMap<String, Integer> tokenizeAndNormalize(String text) {
        HashMap<String, Integer> tokenMap = new HashMap<>();
        StringBuilder word = new StringBuilder();
        text = text.toLowerCase();

        for (char c : text.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                word.append(c);
            } else if (word.length() > 0) {
                String token = word.toString();
                tokenMap.put(token, tokenMap.getOrDefault(token, 0) + 1);
                word.setLength(0);
            }
        }

        if (word.length() > 0) {
            String token = word.toString();
            tokenMap.put(token, tokenMap.getOrDefault(token, 0) + 1);
        }

        return tokenMap;
    }

    public static double calculateSimilarityHashMap(String text1, String text2) {
        HashMap<String, Integer> map1 = tokenizeAndNormalize(text1);
        HashMap<String, Integer> map2 = tokenizeAndNormalize(text2);

        int intersectionSize = 0;
        int unionSize = 0;

        long startTimeIntersection = System.nanoTime();
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            if (map2.containsKey(entry.getKey())) {
                intersectionSize++;
            }
        }
        long endTimeIntersection = System.nanoTime();
        long durationIntersection = endTimeIntersection - startTimeIntersection;
        System.out.println("Time taken for intersection operation: " + durationIntersection + " ns");

        long startTimeUnion = System.nanoTime();
        unionSize = map1.size();
        for (String key : map2.keySet()) {
            if (!map1.containsKey(key)) {
                unionSize++;
            }
        }
        long endTimeUnion = System.nanoTime();
        long durationUnion = endTimeUnion - startTimeUnion;
        System.out.println("Time taken for union operation: " + durationUnion + " ns");

        return (double) intersectionSize / unionSize * 100;
    }
}
