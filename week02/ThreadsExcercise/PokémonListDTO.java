package ThreadsExcercise;

import java.util.Arrays;

public class PokémonListDTO {

    private String count;
    private PokémonDTO[] results;


    @Override
    public String toString() {
        return "PokémonListDTO{" +
                "count='" + count + '\'' +
                ", results=" + Arrays.toString(results) +
                '}';
    }
}
