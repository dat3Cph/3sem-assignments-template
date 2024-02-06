package DTOExcercise;

import java.time.LocalDate;
import java.util.Arrays;

public class ResultDTO extends Media {

    MovieDTO[] movie_results;
    MovieDTO[] results;

    @Override
    public String toString() {
        return "ResultDTO{" +
                "results=" + Arrays.toString(this.movie_results) +
                '}';
    }

}
