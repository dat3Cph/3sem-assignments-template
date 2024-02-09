package ThreadsExcercise;

public class DadJokeDTO {

    private String id;
    private String joke;
    private String status;

    @Override
    public String toString() {
        return "DadJokeDTO{" +
                "id='" + id + '\'' +
                ", joke='" + joke + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
