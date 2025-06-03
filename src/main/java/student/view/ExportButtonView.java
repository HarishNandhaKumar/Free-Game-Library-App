package student.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 * ExportButtonView is a JPanel that contains a button for exporting results.
 */
public class ExportButtonView extends JPanel {
    /**
     * The button for exporting results.
     */
    private JButton exportButton;
    /**
     * The button for loading lists.
     */
    private JButton loadListButton;
    /**
     * The file chooser for selecting files.
     */
    private JFileChooser fileChooser;

    /**
     * Constructs a new ExportButtonView.
     * Initializes the export button and sets up the panel layout.
     */
    public ExportButtonView() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        loadListButton = new JButton("Load List");
        add(loadListButton, gbc);
    
        gbc.gridy = 1;
        exportButton = new JButton("Export");
        add(exportButton, gbc);

        // Initialize JFileChooser
        fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fileChooser.setDialogTitle("Export List");
        /*
         * feel free to delete file formats that you don't want to support
         */
        fileChooser.setFileFilter(new FileNameExtensionFilter("XML (*.xml)", "xml"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON (*.json)", "json"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV (*.csv)", "csv"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text (*.txt)", "txt"));
    }

    /**
     * Adds an ActionListener to the export button.
     * @param listener the ActionListener to be added
     */
    public void addExportListener(ActionListener listener) {
        exportButton.addActionListener(listener);
        
    }

    /**
     * Adds an ActionListener to the load list button.
     * @param listener the ActionListener to be added
     */
    public void setLoadListButtonListener(ActionListener listener) {
        loadListButton.addActionListener(listener);
    }
    
    /**
     * Displays the save dialog and returns the user's action.
     * @return the return state of the file chooser on pop-up
     */
    public int showSaveDialog() {
        return fileChooser.showSaveDialog(this);
    }

    /**
     * Gets the selected file from the file chooser.
     * @return the selected file
     */
    public File getSelectedFile() {
        return fileChooser.getSelectedFile();
    }

    /**
     * Gets the selected file extension from the file chooser.
     * @return the selected file extension
     */
    public String getFileExtension() {
        File selectedFile = getSelectedFile();
        if (selectedFile == null) {
            return "";
        }
        String fileName = selectedFile.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return "";
        }
        return fileName.substring(dotIndex + 1).toLowerCase();
    }

    /**
     * This method gets the filter extension filter and returns it.
     * @return it returns the filter.
     */
    public FileNameExtensionFilter getFileFilter() {
        return (FileNameExtensionFilter) fileChooser.getFileFilter();
    }
}
