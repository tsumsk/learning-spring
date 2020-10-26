public class GrammyGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("GrammyGuitarist sing() called");
    }

    public void sing2() {
        System.out.println("GrammyGuitarist sing2() called");
    }

    public void rest() {
        System.out.println("GrammyGuitarist rest() called");
    }

    public void talk() {
        System.out.println("GrammyGuitarist talk() called");
    }
}
