package student.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * A class represents a free game item with various attributes such as title, genre, platform, etc.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "Games")
@JsonPropertyOrder({"id", "title", "thumbnail", "short_description", "game_url", "genre",
        "platform", "publisher", "developer", "release_date", "freetogame_profile_url"})
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FreeGameItem {

    /** The unique identifier of the game item. */
    private final int id;

    /** The title of the game. */
    private final String title;

    /** The URL of the game's thumbnail image. */
    private final String thumbnail;

    /** A brief description of the game. */
    private final String shortDescription;

    /** The URL to access the game. */
    private final String gameUrl;

    /** The genre of the game. */
    private final String genre;

    /** The platform on which the game can be played. */
    private final String platform;

    /** The publisher of the game. */
    private final String publisher;

    /** The developer of the game. */
    private final String developer;

    /** The release date of the game. */
    private final String releaseDate;

    /** The URL to the game's profile on the FreeToGame website. */
    private final String freetogameProfileUrl;

    /**
     * Constructs a new FreeGameItem with the specified details.
     *
     * @param id                  the unique identifier of the game item
     * @param title               the title of the game
     * @param thumbnail           the URL of the game's thumbnail image
     * @param shortDescription    a brief description of the game
     * @param gameUrl             the URL to access the game
     * @param genre               the genre of the game
     * @param platform            the platform on which the game can be played
     * @param publisher           the publisher of the game
     * @param developer           the developer of the game
     * @param releaseDate         the release date of the game
     * @param freetogameProfileUrl the URL to the game's profile on the FreeToGame website
     */
    @JsonCreator
    public FreeGameItem(
        @JsonProperty("id") int id,
        @JsonProperty("title") String title,
        @JsonProperty("thumbnail") String thumbnail,
        @JsonProperty("short_description") String shortDescription,
        @JsonProperty("game_url") String gameUrl,
        @JsonProperty("genre") String genre,
        @JsonProperty("platform") String platform,
        @JsonProperty("publisher") String publisher,
        @JsonProperty("developer") String developer,
        @JsonProperty("release_date") String releaseDate,
        @JsonProperty("freetogame_profile_url") String freetogameProfileUrl
    ) {
        this.id = id;
        this.title = title;
        this.thumbnail = thumbnail;
        this.shortDescription = shortDescription;
        this.gameUrl = gameUrl;
        this.genre = genre;
        this.platform = platform;
        this.publisher = publisher;
        this.developer = developer;
        this.releaseDate = releaseDate;
        this.freetogameProfileUrl = freetogameProfileUrl;
    }

    /**
     * @return the unique identifier of the game item
     */
    public int getId() {
        return id;
    }

    /**
     * @return the title of the game
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the URL of the game's thumbnail image
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * @return a brief description of the game
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * @return the URL to access the game
     */
    public String getGameUrl() {
        return gameUrl;
    }

    /**
     * @return the genre of the game
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @return the platform on which the game can be played
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @return the publisher of the game
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @return the developer of the game
     */
    public String getDeveloper() {
        return developer;
    }

    /**
     * @return the release date of the game
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * @return the URL to the game's profile on the FreeToGame website
     */
    public String getFreetogameProfileUrl() {
        return freetogameProfileUrl;
    }

    @Override
    public String toString() {
        return "FreeGameItem{"
            + "id=" + id 
            + ", title='" + title + '\''
            + ", thumbnail='" + thumbnail + '\''
            + ", shortDescription='" + shortDescription + '\''
            + ", gameUrl='" + gameUrl + '\''
            + ", genre='" + genre + '\''
            + ", platform='" + platform + '\''
            + ", publisher='" + publisher + '\''
            + ", developer='" + developer + '\''
            + ", releaseDate='" + releaseDate + '\''
            + ", freetogameProfileUrl='" + freetogameProfileUrl + '\''
            + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FreeGameItem that = (FreeGameItem) o;
        return id == that.id 
            && Objects.equals(title, that.title) 
            && Objects.equals(thumbnail, that.thumbnail)
            && Objects.equals(shortDescription, that.shortDescription) 
            && Objects.equals(gameUrl, that.gameUrl)
            && Objects.equals(genre, that.genre) 
            && Objects.equals(platform, that.platform)
            && Objects.equals(publisher, that.publisher) 
            && Objects.equals(developer, that.developer)
            && Objects.equals(releaseDate, that.releaseDate) 
            && Objects.equals(freetogameProfileUrl, that.freetogameProfileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id, title, thumbnail, shortDescription, gameUrl, genre, 
            platform, publisher, developer, releaseDate, freetogameProfileUrl
        );
    }
}
