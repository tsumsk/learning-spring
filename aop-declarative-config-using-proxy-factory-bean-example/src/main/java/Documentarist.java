public class Documentarist {
    private GrammyGuitarist grammyGuitarist;

    public void setGrammyGuitarist(GrammyGuitarist grammyGuitarist) {
        this.grammyGuitarist = grammyGuitarist;
    }

    public void execute() {
        grammyGuitarist.sing();
        grammyGuitarist.talk();
    }
}
