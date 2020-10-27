public class GrammyGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("GrammyGuitarist sing() called");
    }

    @AdviceRequired
    public void sing(int x) {
        System.out.println("GrammyGuitarist sing(int) called");
    }

    public void rest() {
        System.out.println("GrammyGuitarist rest() called");
    }

    public void talk() {
        System.out.println("GrammyGuitarist talk() called");
    }
}
