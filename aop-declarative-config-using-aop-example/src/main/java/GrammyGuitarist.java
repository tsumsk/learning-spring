public class GrammyGuitarist {
    public void sing() {
        System.out.println("GrammyGuitarist sing() called");
    }

    public void sing(Guitar guitar) {
        System.out.printf("GrammyGuitarist sing(Guitar) called, brand={%s}\n", guitar.getBrand());
    }

    public void rest() {
        System.out.println("GrammyGuitarist rest() called");
    }

    public void talk() {
        System.out.println("GrammyGuitarist talk() called");
    }
}
