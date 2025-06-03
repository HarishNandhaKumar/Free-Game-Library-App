package student.view;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * SortFilterView is a JPanel that contains buttons for sorting, filtering, and listing actions.
 */
public class SortFilterView extends JPanel {
    /**
     * The button for sorting items.
     */
    private JButton sortButton;
    /**
     * The button for adding all items.
     */
    private JButton addAllButton;
    /**
     * The button for removing all items.
     */
    private JButton removeAllButton;
    /**
     * The button for filtering items.
     */
    private JButton filterButton;
    /**
     * The button for listing items.
     */
    private JButton listButton;

    /**
     * Constructs a new SortFilterButtonsView.
     * Initializes the buttons and sets up the panel layout.
     */
    public SortFilterView() {
        setLayout(new GridLayout(1, 5));
        sortButton = new JButton("Sort");
        addAllButton = new JButton("Add All");
        removeAllButton = new JButton("Remove All");
        filterButton = new JButton("Filter");
        listButton = new JButton("List");

        add(sortButton);
        add(addAllButton);
        add(removeAllButton);
        add(filterButton);
        add(listButton);
    }

    /**
     * Gets the sort button.
     *
     * @return the JButton for sorting items
     */
    public JButton getSortButton() {
        return sortButton;
    }

    /**
     * Gets the add all button.
     *
     * @return the JButton for adding all items
     */
    public JButton getAddAllButton() {
        return addAllButton;
    }

    /**
     * Gets the remove all button.
     *
     * @return the JButton for removing all items
     */
    public JButton getRemoveAllButton() {
        return removeAllButton;
    }

    /**
     * Gets the filter button.
     *
     * @return the JButton for filtering items
     */
    public JButton getFilterButton() {
        return filterButton;
    }

    /**
     * This method returns the list button.
     * @return the JButton type object associated with the list button.
     */
    public JButton getListButton() {
        return listButton;
    }

    /**
     * This method sets up the Action Listener to the list button.
     * @param listButtonListener the Action Listener is given as input.
     */
    public void setListButtonListener(ActionListener listButtonListener) {
        getListButton().addActionListener(listButtonListener);
    }

    /**
     * This method sets up the Action Listener to the Filter button.
     * @param filterButtonListener the Action Listener is given as input.
     */
    public void setFilterButtonListener(ActionListener filterButtonListener) {
        getFilterButton().addActionListener(filterButtonListener);
    }

    /**
     * This method sets up the Action Listener to the Sort button.
     * @param sortButtonListener the Action Listener is given as input.
     */
    public void setSortButtonListener(ActionListener sortButtonListener) {
        getSortButton().addActionListener(sortButtonListener);
    }

    /**
     * This method sets up the Action Listener to the Add All button.
     * @param addAllButtonListener the Action Listener is given as input.
     */
    public void setAddAllButtonListener(ActionListener addAllButtonListener) {
        getAddAllButton().addActionListener(addAllButtonListener);
    }

    /**
     * This method sets up the Action Listener to the Remove All button.
     * @param removeAllButtonListener the Action Listener is given as input.
     */
    public void setRemoveAllButtonListener(ActionListener removeAllButtonListener) {
        getRemoveAllButton().addActionListener(removeAllButtonListener);
    }
}
