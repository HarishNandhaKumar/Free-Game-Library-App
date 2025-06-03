import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import student.controller.Controller;
import student.model.FreeGameItem;
import student.model.ItemModelImpl;
import student.model.Sorting;
import student.model.formatters.Display;
import student.model.formatters.Filter;
import student.model.formatters.Formats;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ItemModelImplTest {

    private ItemModelImpl model;
    private Controller controller;

    @BeforeEach
    public void setUp() throws Exception {
        model = new ItemModelImpl();
        controller = new Controller(model);
    }

    @Test
    public void testSaveList(@TempDir Path tempDir) {
        // Prepare some test data
        FreeGameItem game1 = new FreeGameItem(1, "Game1", "thumbnail1", "shortDescription1", "gameUrl1", "genre1", "platform1", "publisher1", "developer1", "releaseDate1", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(2, "Game2", "thumbnail2", "shortDescription2", "gameUrl2", "genre2", "platform2", "publisher2", "developer2", "releaseDate2", "freetogameProfileUrl2");
        model.addItem(game1);
        model.addItem(game2);

        // Save the list to a file in the temporary directory
        Path testFilePath = tempDir.resolve("test_freegamerecords.json");
        model.saveList(testFilePath.toString());

        // Verify the file was created
        File file = testFilePath.toFile();
        assertTrue(file.exists(), "The file should exist after saving the list.");
        assertTrue(file.length() > 0, "The file should not be empty.");
    }

    @Test
    public void testLoadList(@TempDir Path tempDir) {
        // Prepare some test data and save it first
        FreeGameItem game1 = new FreeGameItem(1, "Game1", "thumbnail1", "shortDescription1", "gameUrl1", "genre1", "platform1", "publisher1", "developer1", "releaseDate1", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(2, "Game2", "thumbnail2", "shortDescription2", "gameUrl2", "genre2", "platform2", "publisher2", "developer2", "releaseDate2", "freetogameProfileUrl2");
        model.addItem(game1);
        model.addItem(game2);
        Path testFilePath = tempDir.resolve("test_freegamerecords.json");
        model.saveList(testFilePath.toString());

        // Create a new model instance and load the list from the file
        ItemModelImpl newModel = new ItemModelImpl();
        newModel.loadListCsv(testFilePath.toString());

        // Verify the loaded list
        assertEquals(2, model.getGameList().size(), "The loaded list should contain 2 items.");
        assertEquals(Arrays.asList(game1, game2), model.getGameList(), "The loaded list should contain the original items.");
    }

    @Test
    public void testSearchByNameCaseInsensitive() {
        // Prepare some test data
        FreeGameItem game1 = new FreeGameItem(1, "Game1", "thumbnail1", "shortDescription1", "gameUrl1", "genre1", "platform1", "publisher1", "developer1", "releaseDate1", "freetogameProfileUrl1");
        model.addItem(game1);

        // Search for the game with different cases
        FreeGameItem result1 = model.searchByName("game1");
        FreeGameItem result2 = model.searchByName("GAME1");
        FreeGameItem result3 = model.searchByName("GaMe1");

        // Verify the search results are the same
        assertEquals(game1, result1, "The search should return the correct game (case-insensitive).");
        assertEquals(game1, result2, "The search should return the correct game (case-insensitive).");
        assertEquals(game1, result3, "The search should return the correct game (case-insensitive).");
    }

    //new tests written below.
    @Test
    public void TestSearchByName1() {
        String name = "Tarisland";
        String output;
        output = controller.getSingleGame(name);
        String expectedOutput =
                """ 
                        [{
                        "id":582,
                        "title":"Tarisland",
                        "thumbnail":"https://www.freetogame.com/g/582/thumbnail.jpg",
                        "short_description":"Across-platformMMORPGdevelopedbyLevelInfiniteandPublishedbyTencent.",
                        "game_url":"https://www.freetogame.com/open/tarisland",
                        "genre":"MMORPG",
                        "platform":"PC(Windows)",
                        "publisher":"Tencent",
                        "developer":"LevelInfinite",
                        "release_date":"2024-06-22",
                        "freetogame_profile_url":"https://www.freetogame.com/tarisland"
                        }]
                """.replace(" ", "").replace("\n", "");
        assertEquals(expectedOutput, output.replace(" ", "").replaceAll("\\r\\n",
                "\n").replace("\n", ""));
    }

    @Test
    public void TestSearchByName2() {
        String name = "Overwatch 2";
        String output;
        output = controller.getSingleGame(name);
        String expectedOutput =
                """
                        [ {
                          "id" : 540,
                          "title" : "Overwatch 2",
                          "thumbnail" : "https://www.freetogame.com/g/540/thumbnail.jpg",
                          "short_description" : "A hero-focused first-person team shooter from Blizzard Entertainment.",
                          "game_url" : "https://www.freetogame.com/open/overwatch-2",
                          "genre" : "Shooter",
                          "platform" : "PC (Windows)",
                          "publisher" : "Activision Blizzard",
                          "developer" : "Blizzard Entertainment",
                          "release_date" : "2022-10-04",
                          "freetogame_profile_url" : "https://www.freetogame.com/overwatch-2"
                        } ]
                """.replace(" ", "").replace("\n", "");
        assertEquals(expectedOutput, output.replace(" ", "").replaceAll("\\r\\n",
                "\n").replace("\n", ""));
    }

    @Test
    public void TestAddGame() {
        FreeGameItem game1 = new FreeGameItem(1, "Tarisland", "thumbnail1", "shortDescription1", "gameUrl1", "genre1", "platform1", "publisher1", "developer1", "releaseDate1", "freetogameProfileUrl1");
        model.addItem(game1);
        String expected = "Tarisland";
        assertEquals(expected, model.getGameList().get(0).getTitle());
    }

    @Test
    public void TestRemoveGame() {
        FreeGameItem game1 = new FreeGameItem(1, "Tarisland", "thumbnail1", "shortDescription1", "gameUrl1", "genre1", "platform1", "publisher1", "developer1", "releaseDate1", "freetogameProfileUrl1");
        model.addItem(game1);
        model.removeItem(game1);
        assertTrue(model.getGameList().isEmpty());
    }

    @Test
    public void TestSortIdAscending() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Game1", "thumbnail1", "shortDescription1", "gameUrl1", "genre1", "platform1", "publisher1", "developer1", "releaseDate1", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Game2", "thumbnail2", "shortDescription2", "gameUrl2", "genre2", "platform2", "publisher2", "developer2", "releaseDate2", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Game3", "thumbnail2", "shortDescription2", "gameUrl2", "genre2", "platform2", "publisher2", "developer2", "releaseDate2", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        List<FreeGameItem> output = Sorting.sortItems(gamesList, "id", true);
        List <Integer> result = new ArrayList<>();
        String expectedOutput =
                """
                [12, 24, 36]
                """.replace("\n", "");
        output.forEach(bg -> result.add(bg.getId()));
        assertEquals(expectedOutput, result.toString());
    }

    @Test
    public void TestSortIdDescending() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Game1", "thumbnail1", "shortDescription1", "gameUrl1", "genre1", "platform1", "publisher1", "developer1", "releaseDate1", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Game2", "thumbnail2", "shortDescription2", "gameUrl2", "genre2", "platform2", "publisher2", "developer2", "releaseDate2", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Game3", "thumbnail2", "shortDescription2", "gameUrl2", "genre2", "platform2", "publisher2", "developer2", "releaseDate2", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        List<FreeGameItem> output = Sorting.sortItems(gamesList, "id", false);
        List <Integer> result = new ArrayList<>();
        String expectedOutput =
                """
                [36, 24, 12]
                """.replace("\n", "");
        output.forEach(bg -> result.add(bg.getId()));
        assertEquals(expectedOutput, result.toString());
    }

    @Test
    public void TestSortTitleAscending() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Street Fighter", "thumbnail1", "shortDescription1", "gameUrl1", "genre1", "platform1", "publisher1", "developer1", "releaseDate1", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Ace Combat", "thumbnail2", "shortDescription2", "gameUrl2", "genre2", "platform2", "publisher2", "developer2", "releaseDate2", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Grand Theft Auto", "thumbnail2", "shortDescription2", "gameUrl2", "genre2", "platform2", "publisher2", "developer2", "releaseDate2", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        List<FreeGameItem> output = Sorting.sortItems(gamesList, "title", true);
        List <String> result = new ArrayList<>();
        String expectedOutput =
                """
                [Ace Combat, Grand Theft Auto, Street Fighter]
                """.replace("\n", "");
        output.forEach(bg -> result.add(bg.getTitle()));
        assertEquals(expectedOutput, result.toString());
    }

    @Test
    public void TestSortTitleDescending() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Street Fighter", "thumbnail1", "shortDescription1", "gameUrl1", "genre1", "platform1", "publisher1", "developer1", "releaseDate1", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Ace Combat", "thumbnail2", "shortDescription2", "gameUrl2", "genre2", "platform2", "publisher2", "developer2", "releaseDate2", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Grand Theft Auto", "thumbnail2", "shortDescription2", "gameUrl2", "genre2", "platform2", "publisher2", "developer2", "releaseDate2", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        List<FreeGameItem> output = Sorting.sortItems(gamesList, "title", false);
        List <String> result = new ArrayList<>();
        String expectedOutput =
                """
                [Street Fighter, Grand Theft Auto, Ace Combat]
                """.replace("\n", "");
        output.forEach(bg -> result.add(bg.getTitle()));
        assertEquals(expectedOutput, result.toString());
    }

    @Test
    public void TestSortGenreAscending() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Street Fighter", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "publisher1", "developer1", "releaseDate1", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "publisher2", "developer2", "releaseDate2", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Grand Theft Auto", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "publisher2", "developer2", "releaseDate2", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        List<FreeGameItem> output = Sorting.sortItems(gamesList, "genre", true);
        List <String> result = new ArrayList<>();
        String expectedOutput =
                """
                [Action, Fighting, Horror]
                """.replace("\n", "");
        output.forEach(bg -> result.add(bg.getGenre()));
        assertEquals(expectedOutput, result.toString());
    }

    @Test
    public void TestSortGenreDescending() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Street Fighter", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "publisher1", "developer1", "releaseDate1", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "publisher2", "developer2", "releaseDate2", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Grand Theft Auto", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "publisher2", "developer2", "releaseDate2", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        List<FreeGameItem> output = Sorting.sortItems(gamesList, "genre", false);
        List <String> result = new ArrayList<>();
        String expectedOutput =
                """
                [Horror, Fighting, Action]
                """.replace("\n", "");
        output.forEach(bg -> result.add(bg.getGenre()));
        assertEquals(expectedOutput, result.toString());
    }

    @Test
    public void TestSortReleaseDateAscending() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Street Fighter", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "publisher1", "developer1", "1986-08-16", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "publisher2", "developer2", "2023-02-11", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Grand Theft Auto", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "publisher2", "developer2", "1999-06-05", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        List<FreeGameItem> output = Sorting.sortItems(gamesList, "release_date", true);
        List <String> result = new ArrayList<>();
        String expectedOutput =
                """
                [1986-08-16, 1999-06-05, 2023-02-11]
                """.replace("\n", "");
        output.forEach(bg -> result.add(bg.getReleaseDate()));
        assertEquals(expectedOutput, result.toString());
    }

    @Test
    public void TestSortReleaseDateDescending() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Street Fighter", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "publisher1", "developer1", "1986-08-16", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "publisher2", "developer2", "2023-02-11", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Grand Theft Auto", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "publisher2", "developer2", "1999-06-05", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        List<FreeGameItem> output = Sorting.sortItems(gamesList, "release_date", false);
        List <String> result = new ArrayList<>();
        String expectedOutput =
                """
                [2023-02-11, 1999-06-05, 1986-08-16]
                """.replace("\n", "");
        output.forEach(bg -> result.add(bg.getReleaseDate()));
        assertEquals(expectedOutput, result.toString());
    }

    @Test
    public void TestSortPublisherAscending() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Mad Max: Video Game", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "Warner Bros Games", "developer1", "1986-08-16", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "Capcom", "developer2", "2023-02-11", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Grand Theft Auto", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "Rockstar", "developer2", "1999-06-05", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        List<FreeGameItem> output = Sorting.sortItems(gamesList, "publisher", true);
        List <String> result = new ArrayList<>();
        String expectedOutput =
                """
                [Capcom, Rockstar, Warner Bros Games]
                """.replace("\n", "");
        output.forEach(bg -> result.add(bg.getPublisher()));
        assertEquals(expectedOutput, result.toString());
    }

    @Test
    public void TestSortPublisherDescending() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Mad Max: Video Game", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "Warner Bros Games", "developer1", "1986-08-16", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "Capcom", "developer2", "2023-02-11", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Grand Theft Auto", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "Rockstar", "developer2", "1999-06-05", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        List<FreeGameItem> output = Sorting.sortItems(gamesList, "publisher", false);
        List <String> result = new ArrayList<>();
        String expectedOutput =
                """
                [Warner Bros Games, Rockstar, Capcom]
                """.replace("\n", "");
        output.forEach(bg -> result.add(bg.getPublisher()));
        assertEquals(expectedOutput, result.toString());
    }

    @Test
    public void TestSortDeveloperAscending() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Mad Max: Video Game", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "Warner Bros Games", "Warner Interactive", "1986-08-16", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "Capcom", "Capcom Studio", "2023-02-11", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Grand Theft Auto", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "Rockstar", "Rockstar LA", "1999-06-05", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        List<FreeGameItem> output = Sorting.sortItems(gamesList, "publisher", true);
        List <String> result = new ArrayList<>();
        String expectedOutput =
                """
                [Capcom Studio, Rockstar LA, Warner Interactive]
                """.replace("\n", "");
        output.forEach(bg -> result.add(bg.getDeveloper()));
        assertEquals(expectedOutput, result.toString());
    }

    @Test
    public void TestSortDeveloperDescending() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Mad Max: Video Game", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "Warner Bros Games", "Warner Interactive", "1986-08-16", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "Capcom", "Capcom Studio", "2023-02-11", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Grand Theft Auto", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "Rockstar", "Rockstar LA", "1999-06-05", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        List<FreeGameItem> output = Sorting.sortItems(gamesList, "publisher", false);
        List <String> result = new ArrayList<>();
        String expectedOutput =
                """
                [Warner Interactive, Rockstar LA, Capcom Studio]
                """.replace("\n", "");
        output.forEach(bg -> result.add(bg.getDeveloper()));
        assertEquals(expectedOutput, result.toString());
    }

    @Test
    public void testFilterEquals() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Mad Max: Video Game", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "Warner Bros Games", "Warner Interactive", "1986-08-16", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "Capcom", "Capcom Studio", "2023-02-11", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Grand Theft Auto", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "Rockstar", "Rockstar LA", "1999-06-05", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        Stream <FreeGameItem> gameStream = gamesList.stream();
        List<FreeGameItem> output = Filter.filterSingle(gameStream, "id==36").toList();
        String expectedOutput =
                """
                Mad Max: Video Game
                """.replace("\n", "");
        assertEquals(expectedOutput, output.get(0).getTitle());
    }

    @Test
    public void testFilterContains() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Mad Max: Video Game", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "Warner Bros Games", "Warner Interactive", "1986-08-16", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "Capcom", "Capcom Studio", "2023-02-11", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Resident Evil: Code Veronica", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "Rockstar", "Rockstar LA", "1999-06-05", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        Stream <FreeGameItem> gameStream = gamesList.stream();
        List<FreeGameItem> output = Filter.filterSingle(gameStream, "title~=Evil").toList();
        String expectedOutput =
                """
                [Resident Evil, Resident Evil: Code Veronica]
                """.replace("\n", "");
        List <String> result = new ArrayList<>();
        output.forEach(bg -> result.add(bg.getTitle()));
        assertEquals(expectedOutput, result.toString());
    }

    @Test
    public void testFilterNotEqual() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Mad Max: Video Game", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "Warner Bros Games", "Warner Interactive", "1986-08-16", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "Capcom", "Capcom Studio", "2023-02-11", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "God Of War", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "Rockstar", "Rockstar LA", "1999-06-05", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        Stream <FreeGameItem> gameStream = gamesList.stream();
        List<FreeGameItem> output = Filter.filterSingle(gameStream, "title!=God Of War").toList();
        String expectedOutput =
                """
                [Mad Max: Video Game, Resident Evil]
                """.replace("\n", "");
        List <String> result = new ArrayList<>();
        output.forEach(bg -> result.add(bg.getTitle()));
        assertEquals(expectedOutput, result.toString());
    }

    @Test
    public void testFilterGreater() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Mad Max: Video Game", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "Warner Bros Games", "Warner Interactive", "1986-08-16", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "Capcom", "Capcom Studio", "2023-02-11", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Batman Arkham Knight", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "Rockstar", "Rockstar LA", "1999-06-05", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        Stream <FreeGameItem> gameStream = gamesList.stream();
        List<FreeGameItem> output = Filter.filterSingle(gameStream, "id>15").toList();
        String expectedOutput =
                """
                [Mad Max: Video Game, Batman Arkham Knight]
                """.replace("\n", "");
        List <String> result = new ArrayList<>();
        output.forEach(bg -> result.add(bg.getTitle()));
        assertEquals(expectedOutput, result.toString());
    }

    @Test
    public void testFilterGreaterEqual() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Mad Max: Video Game", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "Warner Bros Games", "Warner Interactive", "1986-08-16", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "Capcom", "Capcom Studio", "2023-02-11", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Batman Arkham Knight", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "Rockstar", "Rockstar LA", "1999-06-05", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        Stream <FreeGameItem> gameStream = gamesList.stream();
        List<FreeGameItem> output = Filter.filterSingle(gameStream, "id>=24").toList();
        String expectedOutput =
                """
                [Mad Max: Video Game, Batman Arkham Knight]
                """.replace("\n", "");
        List <String> result = new ArrayList<>();
        output.forEach(bg -> result.add(bg.getTitle()));
        assertEquals(expectedOutput, result.toString());
    }

    @Test
    public void testFilterLesser() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Mad Max: Video Game", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "Warner Bros Games", "Warner Interactive", "1986-08-16", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "Capcom", "Capcom Studio", "2023-02-11", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Batman Arkham Knight", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "Rockstar", "Rockstar LA", "1999-06-05", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        Stream <FreeGameItem> gameStream = gamesList.stream();
        List<FreeGameItem> output = Filter.filterSingle(gameStream, "id<20").toList();
        String expectedOutput =
                """
                [Resident Evil]
                """.replace("\n", "");
        List <String> result = new ArrayList<>();
        output.forEach(bg -> result.add(bg.getTitle()));
        assertEquals(expectedOutput, result.toString());
    }

    @Test
    public void testFilterLesserEqual() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Mad Max: Video Game", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "Warner Bros Games", "Warner Interactive", "1986-08-16", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "Capcom", "Capcom Studio", "2023-02-11", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Batman Arkham Knight", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "Rockstar", "Rockstar LA", "1999-06-05", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        Stream <FreeGameItem> gameStream = gamesList.stream();
        List<FreeGameItem> output = Filter.filterSingle(gameStream, "id<=24").toList();
        String expectedOutput =
                """
                [Resident Evil, Batman Arkham Knight]
                """.replace("\n", "");
        List <String> result = new ArrayList<>();
        output.forEach(bg -> result.add(bg.getTitle()));
        assertEquals(expectedOutput, result.toString());
    }

    @Test
    public void testAddAll() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Mad Max: Video Game", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "Warner Bros Games", "Warner Interactive", "1986-08-16", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "Capcom", "Capcom Studio", "2023-02-11", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Batman Arkham Knight", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "Rockstar", "Rockstar LA", "1999-06-05", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        model.getTempGamesList().addAll(gamesList);
        controller.addAllGamesToList();
        String expectedOutput =
                """
                [Mad Max: Video Game, Resident Evil, Batman Arkham Knight]
                """.replace("\n", "");
        List <String> result = new ArrayList<>();
        model.getTempGamesList().forEach(bg -> result.add(bg.getTitle()));
        assertEquals(expectedOutput, result.toString());
    }

    @Test
    public void testRemoveAll() {
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Mad Max: Video Game", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "Warner Bros Games", "Warner Interactive", "1986-08-16", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "Capcom", "Capcom Studio", "2023-02-11", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Batman Arkham Knight", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "Rockstar", "Rockstar LA", "1999-06-05", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        model.getTempGamesList().addAll(gamesList);
        controller.addAllGamesToList();
        controller.removeAllGamesList();
        assertTrue(model.getGameList().isEmpty());
    }

    @Test
    public void testListJson() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Mad Max: Video Game", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "Warner Bros Games", "Warner Interactive", "1986-08-16", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "Capcom", "Capcom Studio", "2023-02-11", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Batman Arkham Knight", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "Rockstar", "Rockstar LA", "1999-06-05", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        Display.write(gamesList, Formats.JSON, System.out);
        String expectedOutput =
                """
                        [ {
                          "id" : 36,
                          "title" : "Mad Max: Video Game",
                          "thumbnail" : "thumbnail1",
                          "short_description" : "shortDescription1",
                          "game_url" : "gameUrl1",
                          "genre" : "Fighting",
                          "platform" : "platform1",
                          "publisher" : "Warner Bros Games",
                          "developer" : "Warner Interactive",
                          "release_date" : "1986-08-16",
                          "freetogame_profile_url" : "freetogameProfileUrl1"
                        }, {
                          "id" : 12,
                          "title" : "Resident Evil",
                          "thumbnail" : "thumbnail2",
                          "short_description" : "shortDescription2",
                          "game_url" : "gameUrl2",
                          "genre" : "Horror",
                          "platform" : "platform2",
                          "publisher" : "Capcom",
                          "developer" : "Capcom Studio",
                          "release_date" : "2023-02-11",
                          "freetogame_profile_url" : "freetogameProfileUrl2"
                        }, {
                          "id" : 24,
                          "title" : "Batman Arkham Knight",
                          "thumbnail" : "thumbnail2",
                          "short_description" : "shortDescription2",
                          "game_url" : "gameUrl2",
                          "genre" : "Action",
                          "platform" : "platform2",
                          "publisher" : "Rockstar",
                          "developer" : "Rockstar LA",
                          "release_date" : "1999-06-05",
                          "freetogame_profile_url" : "freetogameProfileUrl2"
                        } ]
                """.replace("\n", "").replace(" ", "");
        System.setOut(System.out);
        assertEquals(expectedOutput, outContent.toString().replaceAll("\\r\\n",
                "\n").replace("\n", "").replace(" ", ""));
    }

    @Test
    public void testListXml() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Mad Max: Video Game", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "Warner Bros Games", "Warner Interactive", "1986-08-16", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "Capcom", "Capcom Studio", "2023-02-11", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Batman Arkham Knight", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "Rockstar", "Rockstar LA", "1999-06-05", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        Display.write(gamesList, Formats.XML, System.out);
        String expectedOutput =
                """
                        <GamesList>
                          <games>
                            <id>36</id>
                            <title>Mad Max: Video Game</title>
                            <thumbnail>thumbnail1</thumbnail>
                            <short_description>shortDescription1</short_description>
                            <game_url>gameUrl1</game_url>
                            <genre>Fighting</genre>
                            <platform>platform1</platform>
                            <publisher>Warner Bros Games</publisher>
                            <developer>Warner Interactive</developer>
                            <release_date>1986-08-16</release_date>
                            <freetogame_profile_url>freetogameProfileUrl1</freetogame_profile_url>
                          </games>
                          <games>
                            <id>12</id>
                            <title>Resident Evil</title>
                            <thumbnail>thumbnail2</thumbnail>
                            <short_description>shortDescription2</short_description>
                            <game_url>gameUrl2</game_url>
                            <genre>Horror</genre>
                            <platform>platform2</platform>
                            <publisher>Capcom</publisher>
                            <developer>Capcom Studio</developer>
                            <release_date>2023-02-11</release_date>
                            <freetogame_profile_url>freetogameProfileUrl2</freetogame_profile_url>
                          </games>
                          <games>
                            <id>24</id>
                            <title>Batman Arkham Knight</title>
                            <thumbnail>thumbnail2</thumbnail>
                            <short_description>shortDescription2</short_description>
                            <game_url>gameUrl2</game_url>
                            <genre>Action</genre>
                            <platform>platform2</platform>
                            <publisher>Rockstar</publisher>
                            <developer>Rockstar LA</developer>
                            <release_date>1999-06-05</release_date>
                            <freetogame_profile_url>freetogameProfileUrl2</freetogame_profile_url>
                          </games>
                        </GamesList>
                """.replace("\n", "").replace(" ", "");
        System.setOut(System.out);
        assertEquals(expectedOutput, outContent.toString().replaceAll("\\r\\n",
                "\n").replace("\n", "").replace(" ", ""));
    }

    @Test
    public void testListCsv() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Mad Max: Video Game", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "Warner Bros Games", "Warner Interactive", "1986-08-16", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "Capcom", "Capcom Studio", "2023-02-11", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Batman Arkham Knight", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "Rockstar", "Rockstar LA", "1999-06-05", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        Display.write(gamesList, Formats.CSV, System.out);
        String expectedOutput =
                """
                        id,title,thumbnail,short_description,game_url,genre,platform,publisher,developer,release_date,freetogame_profile_url
                        36,"Mad Max: Video Game",thumbnail1,shortDescription1,gameUrl1,Fighting,platform1,"Warner Bros Games","Warner Interactive",1986-08-16,freetogameProfileUrl1
                        12,"Resident Evil",thumbnail2,shortDescription2,gameUrl2,Horror,platform2,Capcom,"Capcom Studio",2023-02-11,freetogameProfileUrl2
                        24,"Batman Arkham Knight",thumbnail2,shortDescription2,gameUrl2,Action,platform2,Rockstar,"Rockstar LA",1999-06-05,freetogameProfileUrl2
                """.replace(" ", "");
        System.setOut(System.out);
        assertEquals(expectedOutput, outContent.toString().replaceAll("\\r\\n",
                "\n").replace(" ",""));
    }

    @Test
    public void testListPretty() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        List< FreeGameItem> gamesList = new ArrayList<>();
        FreeGameItem game1 = new FreeGameItem(36, "Mad Max: Video Game", "thumbnail1", "shortDescription1", "gameUrl1", "Fighting", "platform1", "Warner Bros Games", "Warner Interactive", "1986-08-16", "freetogameProfileUrl1");
        FreeGameItem game2 = new FreeGameItem(12, "Resident Evil", "thumbnail2", "shortDescription2", "gameUrl2", "Horror", "platform2", "Capcom", "Capcom Studio", "2023-02-11", "freetogameProfileUrl2");
        FreeGameItem game3 = new FreeGameItem(24, "Batman Arkham Knight", "thumbnail2", "shortDescription2", "gameUrl2", "Action", "platform2", "Rockstar", "Rockstar LA", "1999-06-05", "freetogameProfileUrl2");
        gamesList.add(game1);
        gamesList.add(game2);
        gamesList.add(game3);
        Display.write(gamesList, Formats.PRETTY, System.out);
        String expectedOutput =
                """
                        Mad Max: Video Game
                                     ID: 36
                                     Short Description: shortDescription1
                                     Genre: Fighting
                                     Platform: platform1
                                     Publisher: Warner Bros Games
                                     Developer: Warner Interactive
                                     Release Date: 1986-08-16
                                     Thumbnail URL: thumbnail1
                                     Game URL: gameUrl1
                                     freetogame ProfileURL: freetogameProfileUrl1
                        Resident Evil
                                     ID: 12
                                     Short Description: shortDescription2
                                     Genre: Horror
                                     Platform: platform2
                                     Publisher: Capcom
                                     Developer: Capcom Studio
                                     Release Date: 2023-02-11
                                     Thumbnail URL: thumbnail2
                                     Game URL: gameUrl2
                                     freetogame ProfileURL: freetogameProfileUrl2
                        Batman Arkham Knight
                                     ID: 24
                                     Short Description: shortDescription2
                                     Genre: Action
                                     Platform: platform2
                                     Publisher: Rockstar
                                     Developer: Rockstar LA
                                     Release Date: 1999-06-05
                                     Thumbnail URL: thumbnail2
                                     Game URL: gameUrl2
                                     freetogame ProfileURL: freetogameProfileUrl2
                """.replace(" ", "");
        System.setOut(System.out);
        assertEquals(expectedOutput, outContent.toString().replaceAll("\\r\\n",
                "\n").replace(" ", ""));
    }

    @Test
    public void testLoadListJson() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        model.loadListJson("data/testgameslist1.json");
        Display.write(model.getGameList(), Formats.PRETTY, System.out);
        String expectedOutput =
                """
                        Spacelords
                                     ID: 28
                                     Short Description: A free-to-play 4v1 sci-fi shooter.\s
                                     Genre: Shooter
                                     Platform: PC (Windows)
                                     Publisher:  MercurySteam Entertainment
                                     Developer: MercurySteam
                                     Release Date: 2018-09-22
                                     Thumbnail URL: https://www.freetogame.com/g/28/thumbnail.jpg
                                     Game URL: https://www.freetogame.com/open/spacelords
                                     freetogame ProfileURL: https://www.freetogame.com/spacelords
                """.replace(" ", "");
        System.setOut(System.out);
        assertEquals(expectedOutput, outContent.toString().replaceAll("\\r\\n",
                "\n").replace(" ", ""));
    }

    @Test
    public void testLoadListXml() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        model.loadListXml("data/testgamesxml1.xml");
        Display.write(model.getGameList(), Formats.PRETTY, System.out);
        String expectedOutput =
                """
                        Spacelords
                                     ID: 28
                                     Short Description: A free-to-play 4v1 sci-fi shooter.\s
                                     Genre: Shooter
                                     Platform: PC (Windows)
                                     Publisher:  MercurySteam Entertainment
                                     Developer: MercurySteam
                                     Release Date: 2018-09-22
                                     Thumbnail URL: https://www.freetogame.com/g/28/thumbnail.jpg
                                     Game URL: https://www.freetogame.com/open/spacelords
                                     freetogame ProfileURL: https://www.freetogame.com/spacelords
                """.replace(" ", "");
        System.setOut(System.out);
        assertEquals(expectedOutput, outContent.toString().replaceAll("\\r\\n",
                "\n").replace(" ", ""));
    }

    @Test
    public void testLoadListCsv() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        model.loadListCsv("data/testgamecsv1.csv");
        Display.write(model.getGameList(), Formats.PRETTY, System.out);
        String expectedOutput =
                """
                        Spacelords
                                     ID: 28
                                     Short Description: A free-to-play 4v1 sci-fi shooter.\s
                                     Genre: Shooter
                                     Platform: PC (Windows)
                                     Publisher:  MercurySteam Entertainment
                                     Developer: MercurySteam
                                     Release Date: 2018-09-22
                                     Thumbnail URL: https://www.freetogame.com/g/28/thumbnail.jpg
                                     Game URL: https://www.freetogame.com/open/spacelords
                                     freetogame ProfileURL: https://www.freetogame.com/spacelords
                """.replace(" ", "");
        System.setOut(System.out);
        assertEquals(expectedOutput, outContent.toString().replaceAll("\\r\\n",
                "\n").replace(" ", ""));
    }

    @Test
    public void testNegativeSearch1() {
        String result = controller.getSingleGame("xyz");
        assertEquals("No game found", result);
    }

    @Test
    public void testNegativeSearch2() {
        String result = controller.getSingleGame("");
        assertEquals("No game found", result);
    }

    @Test
    public void testNegativeAddAll() {
        List< FreeGameItem> gamesList = new ArrayList<>(); // empty list
        model.getTempGamesList().addAll(gamesList);
        String result = controller.addAllGamesToList();
        String expectedOutput =
                """
                Nothing to add
                """.replace("\n", "");
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testNegativeRemoveAll() {
        List< FreeGameItem> gamesList = new ArrayList<>(); // empty list
        model.getTempGamesList().addAll(gamesList);
        String result = controller.removeAllGamesList();
        String expectedOutput =
                """
                Games List is already empty
                """.replace("\n", "");
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testNegativeRemove1() {
        List< FreeGameItem> gamesList = new ArrayList<>(); // empty list
        model.getTempGamesList().addAll(gamesList);
        String result = controller.removeGameFromList("");
        String expectedOutput =
                """
                No Name given
                """.replace("\n", "");
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testNegativeRemove2() {
        List< FreeGameItem> gamesList = new ArrayList<>(); // empty list
        model.getTempGamesList().addAll(gamesList);
        String result = controller.removeGameFromList("xyz");
        String expectedOutput =
                """
                Game not found/not exist
                """.replace("\n", "");
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testNegativeListAll() {
        List< FreeGameItem> gamesList = new ArrayList<>(); // empty list
        model.getTempGamesList().addAll(gamesList);
        String result = controller.getAllGamesList(Formats.JSON);
        String expectedOutput =
                """
                Empty list
                """.replace("\n", "");
        assertEquals(expectedOutput, result);
    }
}