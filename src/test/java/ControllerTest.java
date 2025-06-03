import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import student.controller.Controller;
import student.model.FreeGameItem;
import student.model.ItemModelImpl;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    private ItemModelImpl model;
    private Controller controller;

    @BeforeEach
    public void setUp() throws Exception {
        model = new ItemModelImpl();
        model.addItem(new FreeGameItem(1, "Genshin Impact", "thumbnail", "shortDescription", "gameUrl", "genre", "platform", "publisher", "developer", "releaseDate", "freetogameProfileUrl"));
        controller = new Controller(model);
    }

    @Test
    public void testGetSingleGame_ExistingGame() {
        String result = controller.getSingleGame("Genshin Impact");
        assertFalse(result.contains("No game found"));
        assertTrue(result.contains("Genshin Impact"));
    }

    @Test
    public void testGetSingleGame_NonExistingGame() {
        String result = controller.getSingleGame("NonExistentGame");
        assertEquals("No game found", result);
    }

    @Test
    public void testGetSingleGame_CaseInsensitive() {
        String result1 = controller.getSingleGame("genshin impact");
        String result2 = controller.getSingleGame("GENSHIN IMPACT");

        assertFalse(result1.contains("No game found"));
        assertTrue(result1.contains("Genshin Impact"));

        assertFalse(result2.contains("No game found"));
        assertTrue(result2.contains("Genshin Impact"));
    }
}
