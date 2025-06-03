package student.model;

import java.util.List;

/**
 * Interface to the item model.
 */
public interface ItemModel {
    /** Do not change the file address! */
    String DATABASE = "data/freegamerecords.json";

    /**
     * Get the list of items.
     *
     * @return the list of items
     */
    List<FreeGameItem> getItems();

    /**
     * Add an item to the list.
     *
     * @param item the item to add
     */
    void addItem(FreeGameItem item);

    /**
     * Remove an item from the list.
     *
     * @param item the item to remove
     */
    void removeItem(FreeGameItem item);

    /**
     * Save the list to a file.
     *
     * @param filePath the path to save the file to
     */
    void saveList(String filePath);

    /**
     * Load the list from a json file.
     *
     * @param filePath the path to load the file from
     */
    void loadListJson(String filePath);
}
