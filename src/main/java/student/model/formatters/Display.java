package student.model.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import student.model.FreeGameItem;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collection;

public final class Display {

    /**
     * Private constructor to prevent instantiation.
     */
    private Display() {
        // empty
    }

    /**
     * Pretty print the data in a human-readable format.
     * @param records the records to print
     * @param out the output stream to write to
     */
    private static void prettyPrint(Collection<FreeGameItem> records, OutputStream out) {
        PrintStream pout = new PrintStream(out);
        for (FreeGameItem record : records) {
            prettySingle(record, pout);
        }
    }

    /**
     * Pretty print a single record.
     * Let this as an example, so you didn't have to worry about spacing.
     * @param record the record to print
     * @param out the output stream to write to
     */
    private static void prettySingle(@Nonnull FreeGameItem record, @Nonnull PrintStream out) {
        out.println(record.getTitle());
        out.println("             ID: " + record.getId());
        out.println("             Short Description: " + record.getShortDescription());
        out.println("             Genre: " + record.getGenre());
        out.println("             Platform: " + record.getPlatform());
        out.println("             Publisher: " + record.getPublisher());
        out.println("             Developer: " + record.getDeveloper());
        out.println("             Release Date: " + record.getReleaseDate());
        out.println("             Thumbnail URL: " + record.getThumbnail());
        out.println("             Game URL: " + record.getGameUrl());
        out.println("             freetogame ProfileURL: " + record.getFreetogameProfileUrl());

    }

    /**
     * Write the data as XML.
     * @param records the records to write
     * @param out the output stream to write to
     */
    private static void writeXmlData(Collection<FreeGameItem> records, OutputStream out) {
        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        XmlWrapper wrapper = new XmlWrapper(records);
        try {
            mapper.writeValue(out, wrapper);
        } catch (IOException e) {
            System.out.println("Error writing xml");
        }
    }


    /**
     * Write the data as JSON.
     * @param records the records to write
     * @param out the output stream to write to
     */
    private static void writeJsonData(Collection<FreeGameItem> records, OutputStream out) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            mapper.writeValue(out, records);
        } catch (IOException e) {
            System.out.println("Error writing json");
        }
    }

    /**
     * Write the data as CSV.
     * @param records the records to write
     * @param out the output stream to write to
     */
    private static void writeCSVData(Collection<FreeGameItem> records, OutputStream out) {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(FreeGameItem.class).withHeader();
        try {
            mapper.writer(schema).writeValue(out, records);
        } catch (IOException e) {
            System.out.println("Error writing csv");
        }
    }

    /**
     * Write the data in the specified format.
     * @param records the records to write
     * @param format the format to write the records in
     * @param out the output stream to write to
     */
    public static void write(@Nonnull Collection<FreeGameItem> records, @Nonnull Formats format,
                             @Nonnull OutputStream out) {

        switch (format) {
            case XML:
                writeXmlData(records, out);
                break;
            case JSON:
                writeJsonData(records, out);
                break;
            case CSV:
                writeCSVData(records, out);
                break;
            case PRETTY:
                prettyPrint(records, out);
            default:
                break;
        }
    }
}
