package DTOExcercise;

import java.util.Arrays;

public class ResultDTO {

    MovieDTO[] movie_results;

    public ResultDTO(MovieDTO[] results){
        this.movie_results = results;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "results=" + Arrays.toString(this.movie_results) +
                '}';
    }
}
