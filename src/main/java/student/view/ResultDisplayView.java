package student.view;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * ResultDisplayView is a JPanel that displays the result of various actions.
 * It includes a text area for displaying the result.
 */
public class ResultDisplayView extends JPanel {
    /**
     * The text field for showing result.
     */
    private JTextArea resultArea;

    /**
     * Constructs a new ResultDisplayPanel.
     * Initializes the result area and sets up the panel layout.
     */
    public ResultDisplayView() {
        setLayout(new BorderLayout());
        resultArea = new JTextArea(20, 30);
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
    }

    /**
     * Gets the text from the result area.
     * 
     * @return the result text
     */
    public String getResultText() {
        return resultArea.getText();
    }

    /**
     * Sets the text in the result area.
     * 
     * @param text the text to be set
     */
    public void setResultText(String text) {
        resultArea.setText(text);
    }

    /**
     * Clears the text in the result area.
     */
    public void clearResultText() {
        resultArea.setText("");
    }
}
