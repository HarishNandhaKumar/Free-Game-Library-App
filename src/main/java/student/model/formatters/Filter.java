package student.model.formatters;

import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import student.model.FreeGameItem;

public final class Filter {

    private Filter() {
        // Private constructor to prevent instantiation
        throw new AssertionError("Utility class should not be instantiated.");
    }

    /**
     * Filters the stream of FreeGameItem objects based on the given filter string and sort criteria.
     *
     * @param games  The stream of FreeGameItem objects to filter.
     * @param filter The filter string specifying the filter operation.
     * @return A filtered stream of FreeGameItem objects.
     * @throws IllegalArgumentException If an invalid filter string or operator is provided.
     */
    public static Stream<FreeGameItem> filterSingle(Stream<FreeGameItem> games, String filter) {
        if (filter == null || filter.trim().isEmpty()) {
            return games;
        }

        Operations op = Operations.getOperatorFromStr(filter);
        if (op == null) {
            throw new IllegalArgumentException("Bad operator provided in filter: " + filter);
        }

        String[] parts = filter.split(Pattern.quote(op.getOperator()));
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }

        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid filter format: " + filter);
        }

        String sortType = parts[0];
        String value = parts[1];

        return games.filter(game -> getFilter(game, sortType, op, value));
    }

    /**
     * Evaluates whether a FreeGameItem object matches the filter criteria.
     *
     * @param game     The FreeGameItem object to evaluate.
     * @param sortType The attribute to filter by.
     * @param op       The operation to apply.
     * @param value    The value to compare against.
     * @return true if the game matches the filter criteria, false otherwise.
     * @throws IllegalArgumentException If an invalid value is provided for comparison.
     */
    private static boolean getFilter(FreeGameItem game, String sortType, Operations op, String value) {
        try {
            return switch (sortType) {
                case "id" -> filterInt(game.getId(), op, Integer.valueOf(value));
                case "title" -> filterString(game.getTitle(), op, value);
                case "thumbnail" -> filterString(game.getThumbnail(), op, value);
                case "short_description" -> filterString(game.getShortDescription(), op, value);
                case "game_url" -> filterString(game.getGameUrl(), op, value);
                case "genre" -> filterString(game.getGenre(), op, value);
                case "platform" -> filterString(game.getPlatform(), op, value);
                case "publisher" -> filterString(game.getPublisher(), op, value);
                case "developer" -> filterString(game.getDeveloper(), op, value);
                case "release_date" -> filterString(game.getReleaseDate(), op, value);
                case "freetogame_profile_url" -> filterString(game.getFreetogameProfileUrl(), op, value);
                default -> throw new IllegalStateException("Unexpected value: " + sortType);
            };
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid value provided in filter: " + value, e);
        }
    }

    /**
     * Filters a string attribute based on the specified operation and value.
     *
     * @param str   The string attribute to filter.
     * @param op    The operation to apply.
     * @param value The value to compare against.
     * @return true if the attribute matches the filter criteria, false otherwise.
     */
    private static boolean filterString(String str, Operations op, String value) {
        str = str.toLowerCase();
        value = value.toLowerCase();
        return switch (op) {
            case EQUALS -> str.equals(value);
            case CONTAINS -> str.contains(value);
            case NOT_EQUAL -> !str.equals(value);
            case GREATER_THAN -> str.compareTo(value) > 0;
            case LESS_THAN -> str.compareTo(value) < 0;
            case GREATER_EQUAL -> str.compareTo(value) >= 0;
            case LESS_EQUAL -> str.compareTo(value) <= 0;
            default -> false;
        };
    }

    /**
     * Filters an integer attribute based on the specified operation and value.
     *
     * @param intVal The integer attribute to filter.
     * @param op     The operation to apply.
     * @param value  The value to compare against.
     * @return true if the attribute matches the filter criteria, false otherwise.
     */
    private static boolean filterInt(Integer intVal, Operations op, Integer value) {
        return switch (op) {
            case EQUALS -> Objects.equals(intVal, value);
            case GREATER_THAN -> intVal > value;
            case LESS_THAN -> intVal < value;
            case GREATER_EQUAL -> intVal >= value;
            case LESS_EQUAL -> intVal <= value;
            case NOT_EQUAL -> !Objects.equals(intVal, value);
            default -> false;
        };
    }
    
}
