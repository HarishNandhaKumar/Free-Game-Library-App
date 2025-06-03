package student.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class Sorting {

    /**
     * default constructor.
     */
    private Sorting() {
        // Private constructor to prevent instantiation
        throw new AssertionError("Utility class should not be instantiated.");
    }
    /**
     * Sorts a list of FreeGameItems based on the specified field and order.
     *
     * @param items     the list of items to be sorted
     * @param field     the field to sort by (e.g., "title", "releaseDate")
     * @param ascending true for ascending order, false for descending
     * @return sorted list of FreeGameItem
     */
    public static List<FreeGameItem> sortItems(List<FreeGameItem>  items, String field, boolean ascending) {
        Comparator<FreeGameItem> comparator = getComparatorByField(field);

        if (!ascending) {
            comparator = comparator.reversed();
        }

        return items.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
    }

    /**
     * Creates a comparator based on the specified field.
     *
     * @param field the field to create the comparator for
     * @return a comparator for the field
     */
    private static Comparator<FreeGameItem> getComparatorByField(String field) {
        switch (field.toLowerCase()) {
            case "id":
                return Comparator.comparing(FreeGameItem::getId);
            case "title":
                return Comparator.comparing(FreeGameItem::getTitle, String.CASE_INSENSITIVE_ORDER);
            case "genre":
                return Comparator.comparing(FreeGameItem::getGenre, String.CASE_INSENSITIVE_ORDER);
            case "release_date":
                return Comparator.comparing(FreeGameItem::getReleaseDate);
            case "publisher":
                return Comparator.comparing(FreeGameItem::getPublisher, String.CASE_INSENSITIVE_ORDER);
            case "developer":
                return Comparator.comparing(FreeGameItem::getDeveloper, String.CASE_INSENSITIVE_ORDER);
            default:
                throw new IllegalArgumentException("Unsupported sort field: " + field);
        }
    }
}
