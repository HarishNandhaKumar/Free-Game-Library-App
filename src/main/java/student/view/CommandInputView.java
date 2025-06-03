package student.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * CommandInputView is a JPanel that contains a text field for entering commands.
 */
public class CommandInputView extends JPanel {
    /**
     * The text field for entering commands.
     */
    private JTextField commandField;

    /**
     * Constructs a new CommandInputView.
     * Initializes the command field and sets up the panel layout.
     */
    public CommandInputView() {
        setLayout(new BorderLayout());
        commandField = new JTextField(30);
        add(commandField, BorderLayout.CENTER);
    }

    /**
     * Gets the command text.
     * 
     * @return the command text
     */
    public String getCommandText() {
        return commandField.getText();
    }

    /**
     * Sets the command text.
     * 
     * @param text the text to be set
     */
    public void setCommandText(String text) {
        commandField.setText(text);
    }

    /**
     * Clears the command text.
     */
    public void clearCommandText() {
        commandField.setText("");
    }
}
