import java.util.TreeSet;

class FindSimilarityTreeSet {

    public static TreeSet<String> tokenizeAndNormalize(String text) {
        TreeSet<String> tokens = new TreeSet<>();
        StringBuilder word = new StringBuilder();
        text = text.toLowerCase();

        for (char c : text.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                word.append(c);
            } else if (word.length() > 0) {
                tokens.add(word.toString());
                word.setLength(0);
            }
        }

        if (word.length() > 0) {
            tokens.add(word.toString());
        }

        return tokens;
    }

    // Compute Jaccard similarity between two TreeSets.
    public static double calculateSimilarityTreeSet(TreeSet<String> set1, TreeSet<String> set2) {
        // Measure the time for intersection operation.
        long startTimeIntersection = System.nanoTime();
        TreeSet<String> intersection = new TreeSet<>(set1);
        intersection.retainAll(set2);
        long endTimeIntersection = System.nanoTime();
        long durationIntersection = endTimeIntersection - startTimeIntersection;
        System.out.println("Time taken for intersection operation: " + durationIntersection + " ns");

        // Measure the time for union operation.
        long startTimeUnion = System.nanoTime();
        TreeSet<String> union = new TreeSet<>(set1);
        union.addAll(set2);
        long endTimeUnion = System.nanoTime();
        long durationUnion = endTimeUnion - startTimeUnion;
        System.out.println("Time taken for union operation: " + durationUnion + " ns");

        // Calculate Jaccard similarity as a percentage of the intersection over the union.
        return (double) intersection.size() / union.size() * 100;
    }
}