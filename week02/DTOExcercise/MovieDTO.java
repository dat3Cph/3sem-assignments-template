package DTOExcercise;

import java.beans.Transient;
import java.time.LocalDate;

public class MovieDTO {

    String title;
    String overview;
    transient LocalDate releaseDate;
    String releaseYear;
    String release_date;

    @Override
    public String toString() {
        return "MovieDTO{" +
                "title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate=" + releaseDate +
                ", releaseYear='" + releaseYear + '\'' +
                '}';
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }
}
