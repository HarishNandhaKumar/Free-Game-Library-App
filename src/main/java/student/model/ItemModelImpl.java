package student.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import student.model.formatters.Display;
import student.model.formatters.Formats;


/**
 * ItemModel class to handle the game items.
 */
public class ItemModelImpl implements ItemModel {

    /** Game list that contains the free games. */
    private List<FreeGameItem> gameList;

    /** HashMap that contains the game name as key and record as value. */
    private Map<String, FreeGameItem> gamesMap;

    /** List to store the temporary Filtered/Sorted games. */
    private List<FreeGameItem> tempGamesList;

    /** Constructor for the ItemModelImpl class. */
    public ItemModelImpl() {
        this.gameList = new ArrayList<>();
        this.gamesMap = new HashMap<>();
        this.tempGamesList = new ArrayList<>();
    }

    /**
     * This method returns the list of games records.
     * @return it returns the list of records.
     */
    @Override
    public List<FreeGameItem> getItems() {
        return List.copyOf(this.gamesMap.values());
    }

    /**
     * This method retrieves value associated with the game title given as input from the gamesMap.
     * @param gameName the game title/name is given as String input.
     * @return it returns the FreeGameItem value associated with that game title key.
     */
    public FreeGameItem getGameFromMap(String gameName) {
        return this.gamesMap.get(gameName.toLowerCase());
    }

    /**
     * This method checks if the game exists in the database.
     * @param gameName the game name given as string.
     * @return it returns boolean true if exists. Else, it returns false.
     */
    public Boolean checkGameExists(String gameName) {
        return this.gamesMap.containsKey(gameName.toLowerCase());
    }

    /**
     * This method checks if the game exists in the user's gameList.
     * @param gameName the game name given as string.
     * @return it returns boolean true if exists. Else, it returns false.
     */
    public Boolean checkGameList(String gameName) {
        for (FreeGameItem gameItem : this.gameList) {
            if (gameItem.getTitle().equals(gameName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method updates the temporary gamesList.
     * @param newGamesList the newly filtered/sorted list of games.
     */
    public void updateTempGameList(List<FreeGameItem> newGamesList) {
        this.tempGamesList.clear();
        this.tempGamesList.addAll(newGamesList);
    }

    /**
     * This method returns the temporary games list.
     * @return it returns the temporary games list.
     */
    public List<FreeGameItem> getTempGamesList() {
        return this.tempGamesList;
    }

    @Override
    public void addItem(FreeGameItem item) {
        this.gamesMap.put(item.getTitle().toLowerCase(), item);
        this.gameList.add(item);
    }

    /**
     * This method returns a list of FreeGameItem objects.
     * @return the list of FreeGameItem objects.
     */
    public List<FreeGameItem> getGameList() {
        return this.gameList;
    }

    @Override
    public void removeItem(FreeGameItem game) {
        this.gamesMap.remove(game.getTitle().toLowerCase());
        this.gameList.remove(game);
    }

    @Override
    public void saveList(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(filePath), this.gameList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save the list to the file.");
        }
    }

    @Override
    public void loadListJson(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonContent;
            Path path = Paths.get(filePath);
            if (isUtf8Encoded(filePath)) {
                jsonContent = Files.readString(path, StandardCharsets.UTF_8); // UTF-8 encoding
            } else {
                Charset encoding = Charset.forName("windows-1252"); // ANSI encoding (Windows-1252)
                jsonContent = Files.readString(path, encoding);
            }
            List<FreeGameItem> loadedList = objectMapper.readValue(jsonContent, new TypeReference<>() { });
            this.gameList.clear();
            this.gameList.addAll(loadedList);
            this.updateTempGameList(this.gameList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load the list from the file.");
        }
    }

    /**
     * This method reads the xml games list file and updates the main games list with new contents.
     * @param filePath the xml file path is given as string.
     */
    public void loadListXml(String filePath) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xmlContent;
            Path path = Paths.get(filePath);
            if (isUtf8Encoded(filePath)) {
                xmlContent = Files.readString(path, StandardCharsets.UTF_8); // UTF-8 encoding
            } else {
                Charset encoding = Charset.forName("windows-1252"); // ANSI encoding (Windows-1252)
                xmlContent = Files.readString(path, encoding);
            }
            List<FreeGameItem> loadedList = xmlMapper.readValue(xmlContent, new TypeReference<>() { });
            this.gameList.clear();
            this.gameList.addAll(loadedList);
            this.updateTempGameList(this.gameList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load the list from the file.");
        }
    }

    /**
     * This method reads the file and converts the contents into a byte array.
     * @param filePath the file path given as string input.
     * @return it returns a boolean true if its valid utf-8 encoding. Else, it returns boolean false.
     * @throws IOException it throws exception if the file cannot be accessed.
     */
    private static boolean isUtf8Encoded(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        byte[] content = Files.readAllBytes(path);
        return isValidUtf8(content);
    }

    /**
     * This method returns if the file contents are valid utf-8 encoded or not.
     * @param bytes the contents of the file in byte array.
     * @return it returns a boolean true or false.
     */
    private static boolean isValidUtf8(byte[] bytes) {
        int i = 0;
        while (i < bytes.length) {
            int b = bytes[i] & 0xFF;

            if ((b & 0x80) == 0) {
                // Single-byte (0x00 to 0x7F) – valid
                i++;
            } else if ((b & 0xE0) == 0xC0) {
                // Two-byte sequence
                if (i + 1 < bytes.length && (bytes[i + 1] & 0xC0) == 0x80) {
                    i += 2;
                } else {
                    return false;
                }
            } else if ((b & 0xF0) == 0xE0) {
                // Three-byte sequence
                if (i + 2 < bytes.length && (bytes[i + 1] & 0xC0) == 0x80 && (bytes[i + 2] & 0xC0) == 0x80) {
                    i += 3;
                } else {
                    return false;
                }
            } else if ((b & 0xF8) == 0xF0) {
                // Four-byte sequence
                if (i + 3 < bytes.length && (bytes[i + 1] & 0xC0) == 0x80 && (bytes[i + 2] & 0xC0) == 0x80
                        && (bytes[i + 3] & 0xC0) == 0x80) {
                    i += 4;
                } else {
                    return false;
                }
            } else {
                // Invalid byte
                return false;
            }
        }
        return true;
    }

    /**
     * This method reads the csv games list file and updates the main games list with new contents.
     * @param filePath the csv file path is given as string.
     */
    public void loadListCsv(String filePath) {
        List<FreeGameItem> items = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            reader.skip(1);

            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                int id = Integer.parseInt(nextLine[0]);
                String title = nextLine[1];
                String thumbnail = nextLine[2];
                String description = nextLine[3];
                String gameUrl = nextLine[4];
                String genre = nextLine[5];
                String platform = nextLine[6];
                String publisher = nextLine[7];
                String developer = nextLine[8];
                String releaseDate = nextLine[9];
                String freeGameUrl = nextLine[10];

                FreeGameItem item = new FreeGameItem(id, title, thumbnail, description, gameUrl,
                        genre, platform, publisher, developer, releaseDate, freeGameUrl);
                items.add(item);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        this.gameList.clear();
        this.gameList.addAll(items);
        this.updateTempGameList(this.gameList);
    }

    /**
     * This method helps to create an instance of ItemModelImpl class.
     * It also creates a hashmap containing game title as key and record as value.
     * @param database the json database file path given as string.
     * @return it returns a newly created instance of ItemModelImpl class.
     * @throws Exception when the instance cannot be created.
     */
    public ItemModelImpl createDatabase(String database) throws Exception {
        File file = new File(database);
        if (file.exists() && file.length() == 0) {
            GamesDatabase instance = new GamesDatabase(); // creates the json database.
        }
        ItemModelImpl itemModelObj = new ItemModelImpl();
        try {
            File jsonFile = new File(database);
            ObjectMapper objectMapper = new ObjectMapper();
            Set<FreeGameItem> recordsSet = objectMapper.readValue(jsonFile, new TypeReference<>() { });
            itemModelObj.gamesMap = recordsSet.stream().collect(Collectors.toMap(item -> item.getTitle().toLowerCase(),
                    item -> item));
        } catch (Exception e) {
            System.out.println("Cannot create an instance");
        }
        return itemModelObj;

    }

    /**
     * This method searches the gamesMap using name and returns the FreeGameItem associated with it.
     * @param name the game name given as string.
     * @return it returns the FreeGameItem object associated with that game name.
     */
    public FreeGameItem searchByName(String name) {
        return this.gamesMap.get(name.toLowerCase());
    }

    /**
     * This method invokes the write method from Display class to print the records as output.
     * @param records the list of records.
     * @param format the format of the output given.
     * @param out the instance of the output stream.
     */
    public void writeRecords(List<FreeGameItem> records, Formats format, OutputStream out) {
        Display.write(records, format, out);
    }
}
