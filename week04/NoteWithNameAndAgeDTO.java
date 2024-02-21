public class NoteWithNameAndAgeDTO {

    private Note note;
    private String name;
    private int age;

    public NoteWithNameAndAgeDTO(Note note, String name, int age) {
        this.note = note;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "NoteWithNameAndAgeDTO{" +
                "note=" + note +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
