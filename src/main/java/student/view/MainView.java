package student.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import student.controller.Controller;
import student.model.formatters.Formats;

/**
 * MainView is a JFrame that combines various views into a single window.
 * It includes panels for command input, action buttons, sort/filter buttons, result display, and export functionality.
 */
public class MainView extends JFrame {
    /**
     * Panel for command input.
     */
    private CommandInputView commandInputView;
    /**
     * Panel for search, add, and remove actions.
     */
    private SearchAddRemoveView searchAddRemoveView;
    /**
     * Panel for sort and filter actions.
     */
    private SortFilterView sortFilterView;
    /**
     * Panel for displaying results.
     */
    private ResultDisplayView resultDisplayView;
    /**
     * Panel for export actions.
     */
    private ExportButtonView exportButtonView;
    /**
     * The controller for handling actions.
     */
    private Controller controller;

    /**
     * Constructs a new MainView.
     * Sets the title, default close operation, size, and layout of the frame.
     * Initializes and adds the various panels to the frame.
     * @param obj the instance of Controller is given as input.
     */
    public MainView(Controller obj) {
        this.controller = obj;
        setTitle("Free Game Library");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLayout(new BorderLayout());

        commandInputView = new CommandInputView();
        searchAddRemoveView = new SearchAddRemoveView();
        sortFilterView = new SortFilterView();
        resultDisplayView = new ResultDisplayView();
        exportButtonView = new ExportButtonView();

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(commandInputView, BorderLayout.NORTH);
        topPanel.add(searchAddRemoveView, BorderLayout.CENTER);
        topPanel.add(sortFilterView, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(resultDisplayView, BorderLayout.CENTER);
        add(exportButtonView, BorderLayout.SOUTH);

        searchAddRemoveView.setSearchListener(this::searchButtonListener);
        searchAddRemoveView.setAddListener(this::addButtonListener);
        searchAddRemoveView.setRemoveListener(this::removeButtonListener);
        sortFilterView.setListButtonListener(this::listButtonListener);
        sortFilterView.setFilterButtonListener(this::filterButtonListener);
        sortFilterView.setSortButtonListener(this::sortButtonListener);
        sortFilterView.setAddAllButtonListener(this::addAllButtonListener);
        exportButtonView.addExportListener(this::exportButtonListener);
        sortFilterView.setRemoveAllButtonListener(this::removeAllButtonListener);
        exportButtonView.setLoadListButtonListener(this::loadListButtonListener);
    }

    /**
     * This method is the listener for the Search button.
     * @param e the action event is taken as input.
     */
    private void searchButtonListener(ActionEvent e) {
        String hostname = commandInputView.getCommandText();
        if (hostname == null || hostname.isEmpty()) {
            resultDisplayView.setResultText("Nothing to Search");
        } else {
            resultDisplayView.setResultText(this.controller.getSingleGame(hostname));
        }
    }

    /**
     * This method is the listener for the Add button.
     * @param e the action event is taken as input.
     */
    private void addButtonListener(ActionEvent e) {
        String input = JOptionPane.showInputDialog(searchAddRemoveView.getRemoveButton(),
                "Enter the Game Name to Add:", "Add Game", JOptionPane.PLAIN_MESSAGE);
        if (input == null || input.isEmpty()) {
            resultDisplayView.setResultText("Nothing to Add");
            return;
        }
        this.controller.addGameToList(input);
    }

    /**
     * This method is the listener for the List button.
     * @param e the action event is taken as input.
     */
    private void listButtonListener(ActionEvent e) {
        String[] options = {"JSON", "XML", "CSV", "PRETTY"};
        String input = (String) JOptionPane.showInputDialog(sortFilterView.getFilterButton(),
                "Choose the Format to Display:", "Formats Menu", JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        switch (input) {
            case "JSON" -> resultDisplayView.setResultText(this.controller.getAllGamesList(Formats.JSON));
            case "XML" -> resultDisplayView.setResultText(this.controller.getAllGamesList(Formats.XML));
            case "CSV" -> resultDisplayView.setResultText(this.controller.getAllGamesList(Formats.CSV));
            case "PRETTY" -> resultDisplayView.setResultText(this.controller.getAllGamesList(Formats.PRETTY));
            default -> throw new IllegalStateException("Unexpected value: " + input);
        }
    }

    /**
     * This method is the listener for the Remove button.
     * @param e the action event is taken as input.
     */
    private void removeButtonListener(ActionEvent e) {
        String input = JOptionPane.showInputDialog(searchAddRemoveView.getRemoveButton(),
                "Enter the Game Name to remove:", "Remove Game", JOptionPane.PLAIN_MESSAGE);
        resultDisplayView.setResultText(this.controller.removeGameFromList(input));
    }

    /**
     * This method is the listener for the Filter button.
     * @param e the action event is taken as input.
     */
    private void filterButtonListener(ActionEvent e) {
        String[] options = {"id", "title", "thumbnail", "short_description", "game_url",
                "genre", "platform", "publisher", "developer", "release_date"};
        String selectedValue = (String) JOptionPane.showInputDialog(sortFilterView.getFilterButton(),
                "Select a filter Parameter:", "Filter Parameter Menu", JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        String[] options2 = {"==", "~=", "!=", ">", "<", ">=", "<="};
        String selectedValue2 = (String) JOptionPane.showInputDialog(sortFilterView.getFilterButton(),
                "Select a filter Type:", "Filter Type Menu", JOptionPane.QUESTION_MESSAGE,
                null, options2, options2[0]);

        String parameterValue = JOptionPane.showInputDialog(searchAddRemoveView.getRemoveButton(),
                "Enter the value:", "Filter Value", JOptionPane.PLAIN_MESSAGE);
        if (selectedValue != null && selectedValue2 != null && parameterValue != null
                && !parameterValue.isEmpty()) {
            String fullString = selectedValue + selectedValue2 + parameterValue;
            resultDisplayView.setResultText(this.controller.filterGames(fullString));
        } else {
            resultDisplayView.setResultText("Filter Incomplete");
        }
    }

    /**
     * This method is the listener for the Sort button.
     * @param e the action event is taken as input.
     */
    private void sortButtonListener(ActionEvent e) {
        String[] options = {"id", "title", "genre", "publisher", "developer", "release_date"};

        String selectedValue = (String) JOptionPane.showInputDialog(sortFilterView.getFilterButton(),
                "Select a Sort Parameter:", "Sort Parameter Menu", JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);

        String[] options2 = {"Ascending", "Descending"};
        String selectedValue2 = (String) JOptionPane.showInputDialog(sortFilterView.getFilterButton(),
                "Select a Sort Type:", "Sort Type Menu", JOptionPane.QUESTION_MESSAGE,
                null, options2, options2[0]);

        if (selectedValue == null || selectedValue2 == null) {
            resultDisplayView.setResultText("Sort option is null");
            return;
        }

        boolean choice = true;
        if (selectedValue2.equals("Descending")) {
            choice = false;
        }

        resultDisplayView.setResultText(this.controller.sortGames(selectedValue, choice));
    }

    /**
     * This method is the listener for the Add All button.
     * @param e the action event is taken as input.
     */
    private void addAllButtonListener(ActionEvent e) {
        resultDisplayView.setResultText(this.controller.addAllGamesToList());
    }

    /**
     * This method is the listener for the Export button.
     * @param e the action event is taken as input.
     */
    private void exportButtonListener(ActionEvent e) {
        int userSelection = exportButtonView.showSaveDialog();
    
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = exportButtonView.getSelectedFile();
    
            if (fileToSave == null) {
                JOptionPane.showMessageDialog(this, "No file selected.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            FileNameExtensionFilter filter = exportButtonView.getFileFilter();
            String[] extensions = filter.getExtensions();
            String extension = extensions[0].toLowerCase();
    
            Formats format;
            if (extension.equals("xml")) {
                format = Formats.XML;
            } else if (extension.equals("csv")) {
                format = Formats.CSV;
            } else if (extension.equals("json")) {
                format = Formats.JSON;
            } else if (extension.equals("pretty")) {
                format = Formats.PRETTY;
            } else {
                JOptionPane.showMessageDialog(this, "Unsupported file format.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String fileName = fileToSave.getAbsolutePath();
            if (!fileName.toLowerCase().endsWith("." + extension)) {
                fileToSave = new File(fileName + "." + extension);
            }
    
            String data = controller.getAllGamesList(format);
            try (OutputStream out = new FileOutputStream(fileToSave)) {
                out.write(data.getBytes());
                JOptionPane.showMessageDialog(this, "Export successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error exporting file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

     /** This method is the listener for the Remove All button.
     * @param e the action event is taken as input.
     */
    private void removeAllButtonListener(ActionEvent e) {
        resultDisplayView.setResultText(this.controller.removeAllGamesList());
    }

    /** This method is the listener for the Load List button.
     * @param e the action event is taken as input.
     */
    public void loadListButtonListener(ActionEvent e) {
        String filePath = null;
        String extension = null;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter jsonFilter = new FileNameExtensionFilter("JSON files (*.json)", "json");
        FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("XML files (*.xml)", "xml");
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV files (*.csv)", "csv");
        fileChooser.setFileFilter(jsonFilter);
        fileChooser.addChoosableFileFilter(xmlFilter);
        fileChooser.addChoosableFileFilter(csvFilter);

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            filePath = fileChooser.getSelectedFile().getPath();
            String fileName = fileChooser.getSelectedFile().getName();
            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
                extension = fileName.substring(dotIndex + 1).toLowerCase();
            }
        }

        this.controller.loadGamesList(filePath, extension);
    }
}
