package ThreadsExcercise;

import java.util.Arrays;

public class ChuckNorrisDTO {

    private String[] categories;
    private String created_at;
    private String icon_url;
    private String id;
    private String updated_at;
    private String url;
    private String value;


    @Override
    public String toString() {
        return "ChuckNorrisDTO{" +
                "categories=" + Arrays.toString(categories) +
                ", created_at='" + created_at + '\'' +
                ", icon_url='" + icon_url + '\'' +
                ", id='" + id + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", url='" + url + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
