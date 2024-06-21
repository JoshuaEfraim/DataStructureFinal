import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlagiarismDetectorGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PlagiarismDetectorGUI::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Plagiarism Detector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Adding some padding

        JLabel titleLabel = new JLabel("Plagiarism Detector");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JTextArea textArea1 = new JTextArea(5, 40);
        textArea1.setLineWrap(true);
        textArea1.setWrapStyleWord(true);
        JScrollPane scrollPane1 = new JScrollPane(textArea1);

        JTextArea textArea2 = new JTextArea(5, 40);
        textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(true);
        JScrollPane scrollPane2 = new JScrollPane(textArea2);

        JButton detectButton = new JButton("Detect Plagiarism");

        String[] detectionMethods = {"LinkedList Method", "HashMap Method", "TreeSet Method"};
        JComboBox<String> methodComboBox = new JComboBox<>(detectionMethods);

        JTextArea resultArea = new JTextArea(5, 40);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);

        detectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text1 = textArea1.getText();
                String text2 = textArea2.getText();
                String selectedMethod = (String) methodComboBox.getSelectedItem();
                String result = "";

                if ("LinkedList Method".equals(selectedMethod)) {
                    // Assuming FindSimilarityLinkedList is implemented similarly
                    double similarity = FindSimilarityLinkedList.calculateSimilarityLinkedList(text1, text2);
                    result = String.format("LinkedList Method:\nJaccard Similarity between the texts: %.2f%%\n", similarity);
                } else if ("HashMap Method".equals(selectedMethod)) {
                    double similarity = FindSimilarityHashMap.calculateSimilarityHashMap(text1, text2);
                    result = String.format("HashMap Method:\nJaccard Similarity between the texts: %.2f%%\n", similarity);
                } else if ("TreeSet Method".equals(selectedMethod)) {
                    double similarity = FindSimilarityTreeSet.calculateSimilarityTreeSet(
                            FindSimilarityTreeSet.tokenizeAndNormalize(text1),
                            FindSimilarityTreeSet.tokenizeAndNormalize(text2)
                    );
                    result = String.format("TreeSet Method:\nJaccard Similarity between the texts: %.2f%%\n", similarity);
                }

                resultArea.setText(result);
            }
        });

        // Add components to the panel with constraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Enter the first paragraph:"), gbc);

        gbc.gridy++;
        panel.add(scrollPane1, gbc);

        gbc.gridy++;
        panel.add(new JLabel("Enter the second paragraph:"), gbc);

        gbc.gridy++;
        panel.add(scrollPane2, gbc);

        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(detectButton, gbc);

        gbc.gridy++;
        panel.add(methodComboBox, gbc);

        gbc.gridy++;
        panel.add(new JLabel("Result:"), gbc);

        gbc.gridy++;
        panel.add(resultScrollPane, gbc);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}
