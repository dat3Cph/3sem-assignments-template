package DTOExcercise;

import java.beans.Transient;
import java.time.LocalDate;

public class MovieDTO extends Media{

    String overview;
    transient LocalDate releaseDate;
    String releaseYear;
    String release_date;
    String vote_average;

    @Override
    public String toString() {
        return "MovieDTO{" +
                "title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate=" + releaseDate +
                ", releaseYear='" + releaseYear + '\'' +
                ", Rating='" + vote_average + '\'' +
                ", Original Release Date:'" + release_date + '\'' +
                '}';
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setReleaseDate(String date) {
        this.release_date = date;
    }


    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public String getRelease_date() {
        return release_date;
    }

}
