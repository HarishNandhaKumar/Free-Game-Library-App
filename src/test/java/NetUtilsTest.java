import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import student.model.net.NetUtils;

import static org.junit.jupiter.api.Assertions.*;

public class NetUtilsTest {
     @Test
    void testGetGamesList() {
        try {
            String gamesList = NetUtils.getGamesList();
            assertNotNull(gamesList, "The games list should not be null.");
            assertTrue(gamesList.startsWith("["), "The games list should start with a '[' character.");
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    void testSearchGameByNameFound() {
        try {
            String gameName = "Tibia";
            JSONObject gameDetails = NetUtils.searchGameByName(gameName);
            assertNotNull(gameDetails, "The game details should not be null.");
            assertEquals(gameName, gameDetails.getString("title"), "The game title should match the searched game name.");
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    void testSearchGameByNameNotFound() {
        try {
            String gameName = "NonExistentGame";
            JSONObject gameDetails = NetUtils.searchGameByName(gameName);
            assertNull(gameDetails, "The game details should be null for a non-existent game.");
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    void testSearchGameByNameCaseInsensitive() {
        try {
            String gameName = "fortnite"; // Lowercase
            JSONObject gameDetails = NetUtils.searchGameByName(gameName);
            assertNotNull(gameDetails, "The game details should not be null.");
            assertEquals("Fortnite", gameDetails.getString("title"), "The game title should match 'Fortnite' regardless of case.");
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    void testSearchGameByNameSpecialCharacters() {
        try {
            String gameName = "Fortnite!"; // With special character
            JSONObject gameDetails = NetUtils.searchGameByName(gameName);
            assertNull(gameDetails, "The game details should be null for a game name with special characters.");
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

}
