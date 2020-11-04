public class Documentarist {
    private GrammyGuitarist grammyGuitarist;

    public void setGrammyGuitarist(GrammyGuitarist grammyGuitarist) {
        this.grammyGuitarist = grammyGuitarist;
    }

    public void execute() {
        grammyGuitarist.sing();
        Guitar guitar = new Guitar();
        grammyGuitarist.sing(guitar);
        guitar.setBrand("Gibson");
        grammyGuitarist.sing(guitar);
        grammyGuitarist.talk();
    }
}
