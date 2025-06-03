package student.model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;

import student.model.net.NetUtils;

/**
 * This class primarily helps to create the .json database file using the json array string.
 * The json array string is obtained form the NetUtils.getGamesList().
 */
public class GamesDatabase {

    /**
     * The default constructor helps to create the .json database file using the json array string.
     * @throws Exception it throws exception if the database cannot be created.
     */
    public GamesDatabase() throws Exception {

        String gamesJson = NetUtils.getGamesList();
        String outputFilePath = "data/freegamerecords.json";
        try {
            JSONArray jsonArray = new JSONArray(gamesJson);
            FileWriter fileWriter = new FileWriter(outputFilePath, StandardCharsets.UTF_8);
            fileWriter.write(jsonArray.toString(4));
            fileWriter.flush();
            fileWriter.close();
            System.out.println("JSON file created: " + outputFilePath);
        } catch (IOException e) {
            System.out.println("The database cannot be created.");
        }
    }
}
