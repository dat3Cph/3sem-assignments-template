package DTOExcercise;

import java.util.Arrays;

public class Main {


    public static void main(String[] args) {
        MediaController<Media> movieController = new MovieController<>();

        ////////////////////////////////////////// CONNECT TO API AND GET A STATUS CODE OF 200 //////////////////////////////////////////////////
        String connection = movieController.getResponseBody("https://api.themoviedb.org/3/authentication");
        System.out.println(connection);
        System.out.println("---------------------------------------------------------------------------------------------------------------");

        ////////////////////////////////////////// RETRIEVE DETAILS ABOUT MOVIE BY TITLE //////////////////////////////////////////////////
        Media spidermanMovie = movieController.getByTitle("Spider-Man");
        System.out.println("Spiderman movie: "+spidermanMovie);
        System.out.println("---------------------------------------------------------------------------------------------------------------");

        ////////////////////////////////////////// RETRIEVE DETAILS ABOUT MOVIE BY ID (IMDB) //////////////////////////////////////////////////
        Media movie = movieController.getById("tt5177120");
        System.out.println(movie);
        System.out.println("---------------------------------------------------------------------------------------------------------------");

        ////////////////////////////////////////// GET ARRAY OF MOVIES BY RATING  //////////////////////////////////////////////////
        Media[] moviesByRatingOfEightPointFive = movieController.getAllByRating("8.5");
        System.out.println(Arrays.toString(moviesByRatingOfEightPointFive));
        System.out.println("---------------------------------------------------------------------------------------------------------------");


        ////////////////////////////////////////// GET ARRAY OF MOVIES BY RELEASE DATE  //////////////////////////////////////////////////
        Media[] moviesByReleaseDate = movieController.getAllByReleaseDate();
        System.out.println(Arrays.toString(moviesByReleaseDate));
        System.out.println("---------------------------------------------------------------------------------------------------------------");






    }








}
