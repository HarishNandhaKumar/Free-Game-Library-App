package student.model.formatters;

/**
 * Represents different operations with their corresponding operators.
 */
public enum Operations {
    /**
     * Represents the equality operation (==).
     */
    EQUALS("=="),

    /**
     * Represents the contains operation (~=).
     */
    CONTAINS("~="),

    /**
     * Represents the greater than operation (>).
     */
    GREATER_THAN(">"),

    /**
     * Represents the less than operation (<).
     */
    LESS_THAN("<"),

    /**
     * Represents the greater than or equal to operation (>=).
     */
    GREATER_EQUAL(">="),

    /**
     * Represents the less than or equal to operation (<=).
     */
    LESS_EQUAL("<="),

    /**
     * Represents the not equal to operation (!=).
     */
    NOT_EQUAL("!=");

    /**
     * The operator associated with the operation.
     */
    private final String operator;

    /**
     * Constructor for Operations enum.
     *
     * @param operator The string representation of the operator.
     */
    Operations(String operator) {
        this.operator = operator;
    }

    /**
     * Retrieves the operator string associated with this operation.
     *
     * @return The operator string.
     */
    public String getOperator() {
        return operator;
    }

    /**
     * Retrieves the Operations enum value based on the provided operator string.
     *
     * @param str The operator string to match.
     * @return The corresponding Operations enum value, or null if no match is found.
     */
    public static Operations getOperatorFromStr(String str) {
        if (str.contains("~=")) {
            return Operations.CONTAINS;
        } else if (str.contains("<=")) {
            return Operations.LESS_EQUAL;
        } else if (str.contains(">=")) {
            return Operations.GREATER_EQUAL;
        } else if (str.contains("!=")) {
            return Operations.NOT_EQUAL;
        } else if (str.contains(">")) {
            return Operations.GREATER_THAN;
        } else if (str.contains("<")) {
            return Operations.LESS_THAN;
        } else if (str.contains("==")) {
            return Operations.EQUALS;
        } else {
            return null;
        }
    }
}
