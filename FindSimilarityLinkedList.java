import java.util.Arrays;
import java.util.LinkedList;

public class FindSimilarityLinkedList {
    public static double calculateSimilarityLinkedList(String text1, String text2) {
        LinkedList<String> words1 = new LinkedList<>(Arrays.asList(text1.toLowerCase().split("\\W+")));
        LinkedList<String> words2 = new LinkedList<>(Arrays.asList(text2.toLowerCase().split("\\W+")));

        LinkedList<String> uniqueWords1 = removeDuplicates(words1);
        LinkedList<String> uniqueWords2 = removeDuplicates(words2);

        long startTimeIntersection = System.nanoTime();
        LinkedList<String> intersection = calculateIntersection(uniqueWords1, uniqueWords2);
        long endTimeIntersection = System.nanoTime();
        long durationIntersection = endTimeIntersection - startTimeIntersection;
        System.out.println("Time taken for intersection operation: " + durationIntersection + " ns");

        long startTimeUnion = System.nanoTime();
        LinkedList<String> union = calculateUnion(uniqueWords1, uniqueWords2);
        long endTimeUnion = System.nanoTime();
        long durationUnion = endTimeUnion - startTimeUnion;
        System.out.println("Time taken for union operation: " + durationUnion + " ns");

        // Calculate similarity as a percentage of the intersection over the union
        return (double) intersection.size() / union.size() * 100;
    }

    private static LinkedList<String> removeDuplicates(LinkedList<String> list) {
        LinkedList<String> result = new LinkedList<>();
        for (String item : list) {
            if (!result.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    private static LinkedList<String> calculateIntersection(LinkedList<String> list1, LinkedList<String> list2) {
        LinkedList<String> intersection = new LinkedList<>();
        for (String item : list1) {
            if (list2.contains(item) && !intersection.contains(item)) {
                intersection.add(item);
            }
        }
        return intersection;
    }

    private static LinkedList<String> calculateUnion(LinkedList<String> list1, LinkedList<String> list2) {
        LinkedList<String> union = new LinkedList<>(list1);
        for (String item : list2) {
            if (!union.contains(item)) {
                union.add(item);
            }
        }
        return union;
    }

    public static void main(String[] args) {
        String text1 = "This is a test. This test is only a test.";
        String text2 = "This is another test. Another simple test.";

        double similarity = calculateSimilarityLinkedList(text1, text2);
        System.out.printf("Jaccard Similarity between the texts: %.2f%%\n", similarity);
    }
}
