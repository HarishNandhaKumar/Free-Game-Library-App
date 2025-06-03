package student;

import student.controller.Controller;
import student.model.ItemModelImpl;
import student.view.MainView;

/**
 * The MainApp class serves as the entry point for the application.
 * It initializes the controller, model, and main view, and makes the main view visible.
 */
public final class MainApp {
    /** Private constructor to prevent instantiation. */
    private MainApp() {
        //empty
    }
    /**
     * The main method which serves as the entry point of the application.
     * 
     * @param args Command line arguments
     * @throws Exception if any exception occurs during the execution
     */
    public static void main(String[] args) throws Exception {
        Controller obj = new Controller(new ItemModelImpl());
        MainView mainView = new MainView(obj);
        mainView.setVisible(true);
    }
    
}
