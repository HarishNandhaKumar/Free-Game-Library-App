
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import student.model.FreeGameItem;
import student.model.formatters.Filter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FilterTest {

    private List<FreeGameItem> games;

    @BeforeEach
    void setUp() {
        games = List.of(
                new FreeGameItem(1, "Game One", "thumb1", "desc1", "url1", "Action", "PC", "Publisher1", "Developer1", "2020-01-01", "profile1"),
                new FreeGameItem(2, "Game Two", "thumb2", "desc2", "url2", "Adventure", "PC", "Publisher2", "Developer2", "2021-01-01", "profile2"),
                new FreeGameItem(3, "Game Three", "thumb3", "desc3", "url3", "Action", "Console", "Publisher3", "Developer3", "2022-01-01", "profile3")
        );
    }

    @Test
    void testFilterByTitleEquals() {
        Stream<FreeGameItem> filteredStream = Filter.filterSingle(games.stream(), "title==Game One");
        List<FreeGameItem> filteredGames = filteredStream.collect(Collectors.toList());

        assertEquals(1, filteredGames.size());
        assertEquals("Game One", filteredGames.get(0).getTitle());
    }

    @Test
    void testFilterByGenreContains() {
        Stream<FreeGameItem> filteredStream = Filter.filterSingle(games.stream(), "genre==Action");
        List<FreeGameItem> filteredGames = filteredStream.collect(Collectors.toList());

        assertEquals(2, filteredGames.size());
        assertTrue(filteredGames.stream().allMatch(game -> game.getGenre().equals("Action")));
    }

    @Test
    void testFilterByPlatformNotEqual() {
        Stream<FreeGameItem> filteredStream = Filter.filterSingle(games.stream(), "platform!=PC");
        List<FreeGameItem> filteredGames = filteredStream.collect(Collectors.toList());

        assertEquals(1, filteredGames.size());
        assertEquals("Console", filteredGames.get(0).getPlatform());
    }

    @Test
    void testFilterByReleaseDateGreater() {
        Stream<FreeGameItem> filteredStream = Filter.filterSingle(games.stream(), "release_date>2021-01-01");
        List<FreeGameItem> filteredGames = filteredStream.collect(Collectors.toList());

        assertEquals(1, filteredGames.size());
        assertEquals("2022-01-01", filteredGames.get(0).getReleaseDate());
    }

    @Test
    void testInvalidFilter() {
        assertThrows(IllegalArgumentException.class, () -> Filter.filterSingle(games.stream(), "invalid_filter"));
    }
}