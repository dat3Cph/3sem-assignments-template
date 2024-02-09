package ThreadsExcercise;

public class ResultDTO {

    private DadJokeDTO dadJoke;
    private ChuckNorrisDTO chuckNorris;
    private KanyeDTO kanye;
    private TrumpDTO trump;
    private PokémonListDTO pokemon;
    private AgeGuessDTO ageGuess;
    private DogImageDTO dogImage;
    private GenderDTO number;
    private CatFactDTO catFact;
    private BoredDTO boredDTO;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ResultDTO {");
        builder.append(dadJoke != null ? " "+dadJoke.toString(): "");
        builder.append(chuckNorris != null ? " "+chuckNorris.toString(): "");
        builder.append(kanye != null ? " "+kanye.toString(): "");
        builder.append(trump != null ? " "+trump.toString(): "");
        builder.append(pokemon != null ? " "+pokemon.toString(): "");
        builder.append(ageGuess != null ? " "+ageGuess.toString(): "");
        builder.append(dogImage != null ? " "+dogImage.toString(): "");
        builder.append(number != null ? " "+number.toString(): "");
        builder.append(catFact != null ? " "+catFact.toString(): "");
        builder.append(boredDTO != null ? " "+boredDTO.toString(): "");
        builder.append(" }");
        return builder.toString();
    }


    public DadJokeDTO getDadJoke() {
        return dadJoke;
    }

    public void setDadJoke(DadJokeDTO dadJoke) {
        this.dadJoke = dadJoke;
    }

    public ChuckNorrisDTO getChuckNorris() {
        return chuckNorris;
    }

    public void setChuckNorris(ChuckNorrisDTO chuckNorris) {
        this.chuckNorris = chuckNorris;
    }

    public KanyeDTO getKanye() {
        return kanye;
    }

    public void setKanye(KanyeDTO kanye) {
        this.kanye = kanye;
    }

    public TrumpDTO getTrump() {
        return trump;
    }

    public void setTrump(TrumpDTO trump) {
        this.trump = trump;
    }

    public PokémonListDTO getPokemon() {
        return pokemon;
    }

    public void setPokemon(PokémonListDTO pokemon) {
        this.pokemon = pokemon;
    }

    public AgeGuessDTO getAgeGuess() {
        return ageGuess;
    }

    public void setAgeGuess(AgeGuessDTO ageGuess) {
        this.ageGuess = ageGuess;
    }

    public DogImageDTO getDogImage() {
        return dogImage;
    }

    public void setDogImage(DogImageDTO dogImage) {
        this.dogImage = dogImage;
    }

    public GenderDTO getNumber() {
        return number;
    }

    public void setNumber(GenderDTO number) {
        this.number = number;
    }

    public CatFactDTO getCatFact() {
        return catFact;
    }

    public void setCatFact(CatFactDTO catFact) {
        this.catFact = catFact;
    }

    public BoredDTO getBoredDTO() {
        return boredDTO;
    }

    public void setBoredDTO(BoredDTO boredDTO) {
        this.boredDTO = boredDTO;
    }
}
