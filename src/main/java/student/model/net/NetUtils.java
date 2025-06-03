package student.model.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A series of classes to help with pulling data from https://www.freetogame.com.
 * You can read more about the API at https://www.freetogame.com/api.
 */
public final class NetUtils {
    /**
     * Base URL for the API request.
     */
    private static final String BASE_URL = "https://www.freetogame.com/api";

    /**
     * Prevent instantiation.
     */
    private NetUtils() {
        // Prevent instantiation
    }

    /**
     * Retrieves a list of all free-to-play games from the FreeToGame API.
     *
     * @return a JSON string containing the list of all free-to-play games
     * @throws Exception if an error occurs during the API request
     */
    public static String getGamesList() throws Exception {
        // Create the URL for the API request
        URL url = new URL(BASE_URL + "/games");

        // Open a connection to the API URL
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET"); // Set the request method to GET

        // Create a BufferedReader to read the response from the API
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        // Read the response line by line and append to the StringBuilder
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        // Close the BufferedReader
        in.close();

        // Disconnect the HTTP connection
        conn.disconnect();

        // Return the response content as a string
        return content.toString();
    }

    /**
     * Searches for a game by its name in the list of free-to-play games.
     *
     * @param gameName the name of the game to search for
     * @return a JSONObject containing the game details, or null if the game is not found
     * @throws Exception if an error occurs during JSON parsing
     */
    public static JSONObject searchGameByName(String gameName) throws Exception {

        // Fetch the list of games from the API
        String gamesListJson = getGamesList();
        JSONArray gamesArray = new JSONArray(gamesListJson);

        // Search for the game by name in the games array
        for (int i = 0; i < gamesArray.length(); i++) {
            JSONObject game = gamesArray.getJSONObject(i);
            if (game.getString("title").equalsIgnoreCase(gameName)) {
                return game; // Return the game details if found
            }
        }
        return null; // Return null if the game is not found
    }

    /**
     * Main method for testing the list obtained from the API and searching for a specific game.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String gamesList = getGamesList();
            System.out.println("Games List: ");
            System.out.println(gamesList);

            // Test searching for a game by name
            String gameName = "Tibia";
            JSONObject gameDetails = searchGameByName(gameName);

            if (gameDetails != null) {
                System.out.println("Game Details for " + gameName + ": ");
                System.out.println(gameDetails.toString(2)); // Pretty print with an indentation of 2
            } else {
                System.out.println("Game " + gameName + " not found.");
            }

            // Test searching for a game by name "Fortnite"
            String gameName2 = "Fortnite";
            JSONObject gameDetails2 = searchGameByName(gameName2);

            if (gameDetails2 != null) {
                System.out.println("Game Details for " + gameName2 + ": ");
                System.out.println(gameDetails2.toString(2)); // Pretty print with an indentation of 2
            } else {
                System.out.println("Game " + gameName2 + " not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
