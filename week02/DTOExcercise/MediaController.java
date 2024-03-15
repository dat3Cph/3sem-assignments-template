package DTOExcercise;

public interface MediaController<T extends Media> {

    String getResponseBody(String url);
    T getById(String id);
    T[] getAllByRating(String rating);
    T[] getAllByReleaseDate();

    T getByTitle(String title);


}
