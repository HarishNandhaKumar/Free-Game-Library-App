package student.model.formatters;

import java.util.Collection;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import student.model.FreeGameItem;

/**
 * This wrapper helps when using Jackson to serialize a list of domain records to xml.
 * Without this, it tries to use <ArrayList> and <item> tags instead of <domainList> and <domain> tags.
 * Suggested use (note you need try/catch with this)
 * <pre>
 * XmlMapper mapper = new XmlMapper();
 * mapper.enable(SerializationFeature.INDENT_OUTPUT);
 * DomainXmlWrapper wrapper = new DomainXmlWrapper(records);
 * mapper.writeValue(out, wrapper);
 * </pre>
 */
@JacksonXmlRootElement(localName = "GamesList")
public final class XmlWrapper {

    /** List of the records. */
    @JacksonXmlElementWrapper(useWrapping = false)
    private Collection<FreeGameItem> games;

    /**
     * Constructor.
     * @param games the records to wrap
     */
    public XmlWrapper(Collection<FreeGameItem> games) {
        this.games = games;
    }
}
