package DTOExcercise;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieControllerTest {

    static Gson gson;
    MediaController<Media> movieController;
    @BeforeEach
    void setup(){
        gson = new GsonBuilder().setPrettyPrinting().create();
        movieController = new MovieController<>();
    }

    @Test
    void getResponseBody() {
        assertNotNull(movieController.getResponseBody("https://api.themoviedb.org/3/authentication"));
        assertEquals("{\"success\":true,\"status_code\":1,\"status_message\":\"Success.\"}", movieController.getResponseBody("https://api.themoviedb.org/3/authentication"));
    }

    @Test
    void getById() {
        assertNotNull(movieController.getById("tt5177120"));
    }

    @Test
    void getAllByRating() {
        MovieDTO[] res = (MovieDTO[]) movieController.getAllByRating("7.5");
        assertNotNull(res);
        assertTrue(Float.parseFloat(res[0].vote_average) > 6);
        assertTrue(Float.parseFloat(res[0].vote_average) < 8);
    }

    @Test
    void getAllByReleaseDate() {
        assertNotNull(movieController.getAllByReleaseDate());
    }

    @Test
    void getByTitle() {
        assertEquals("Spider-Man",movieController.getByTitle("Spider-Man").title);
    }

    @Test
    void getMoviesByTitles(){
        assertEquals("The Shawshank Redemption",movieController.getByTitle("The Shawshank Redemption").title);
        assertEquals("The Godfather",movieController.getByTitle("The Godfather").title);
        assertEquals("The Dark Knight",movieController.getByTitle("The Dark Knight").title);
        assertEquals("The Godfather Part II",movieController.getByTitle("The Godfather: Part II").title);
        assertEquals("The Lord of the Rings: The Return of the King",movieController.getByTitle("The Lord of the Rings: The Return of the King").title);
        assertEquals("Pulp Fiction",movieController.getByTitle("Pulp Fiction").title);
        assertEquals("12 Angry Men",movieController.getByTitle("12 Angry Men").title);
        assertEquals("The Good, the Bad and the Ugly",movieController.getByTitle("The Good, the Bad and the Ugly").title);
        assertEquals("Forrest Gump",movieController.getByTitle("Forrest Gump").title);
        assertEquals("Fight Club",movieController.getByTitle("Fight Club").title);
        assertEquals("Inception",movieController.getByTitle("Inception").title);
    }

}