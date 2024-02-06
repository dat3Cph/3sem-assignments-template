package DTOExcercise;

import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        MediaController<Media> movieController = new MovieController<>();
        String connection = movieController.getResponseBody("https://api.themoviedb.org/3/authentication");
        Media movie = movieController.getById("tt5177120");
        Media[] moviesByRatingOfEightPointFive = movieController.getAllByRating("8.5");
        Media[] moviesByReleaseDate = movieController.getAllByReleaseDate();
        Media spidermanMovie = movieController.getByTitle("Spider-Man");

        System.out.println(connection);
        System.out.println(movie);
        System.out.println(Arrays.toString(moviesByRatingOfEightPointFive));
        System.out.println(Arrays.toString(moviesByReleaseDate));
        System.out.println("Spiderman movie: "+spidermanMovie);
    }








}
