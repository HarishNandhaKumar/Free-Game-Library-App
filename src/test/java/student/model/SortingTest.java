package student.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SortingTest {

    private List<FreeGameItem> items;

    @BeforeEach
    public void setUp() {
        items = new ArrayList<>();
        items.add(new FreeGameItem(1, "Game One", "url1", "Desc1", "url1", "Action", "PC", "Publisher1", "Developer1", "2024-01-01", "url1"));
        items.add(new FreeGameItem(2, "Game Two", "url2", "Desc2", "url2", "Adventure", "Console", "Publisher2", "Developer2", "2024-01-02", "url2"));
        items.add(new FreeGameItem(3, "Another Game", "url3", "Desc3", "url3", "Puzzle", "Mobile", "Publisher3", "Developer3", "2024-01-03", "url3"));
    }

    @Test
    public void testSortItemsByTitleAscending() {
        List<FreeGameItem> sortedItems = Sorting.sortItems(items, "title", true);
        assertEquals("Another Game", sortedItems.get(0).getTitle());
        assertEquals("Game One", sortedItems.get(1).getTitle());
        assertEquals("Game Two", sortedItems.get(2).getTitle());
    }

    @Test
    public void testSortItemsByTitleDescending() {
        List<FreeGameItem> sortedItems = Sorting.sortItems(items, "title", false);
        assertEquals("Game Two", sortedItems.get(0).getTitle());
        assertEquals("Game One", sortedItems.get(1).getTitle());
        assertEquals("Another Game", sortedItems.get(2).getTitle());
    }

    @Test
    public void testSortItemsByReleaseDateAscending() {
        List<FreeGameItem> sortedItems = Sorting.sortItems(items, "release_date", true);
        assertEquals("2024-01-01", sortedItems.get(0).getReleaseDate());
        assertEquals("2024-01-02", sortedItems.get(1).getReleaseDate());
        assertEquals("2024-01-03", sortedItems.get(2).getReleaseDate());
    }

    @Test
    public void testSortItemsByReleaseDateDescending() {
        List<FreeGameItem> sortedItems = Sorting.sortItems(items, "release_date", false);
        assertEquals("2024-01-03", sortedItems.get(0).getReleaseDate());
        assertEquals("2024-01-02", sortedItems.get(1).getReleaseDate());
        assertEquals("2024-01-01", sortedItems.get(2).getReleaseDate());
    }

    @Test
    public void testUnsupportedSortField() {
        assertThrows(IllegalArgumentException.class, () -> {
            Sorting.sortItems(items, "unsupportedField", true);
        });
    }
}
