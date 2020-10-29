public class GrammyGuitarist implements Singer {
    @Override
    public void sing() {
        System.out.println("sing() called");
    }

    public void sing(int x) {
        System.out.println("sing(int) called");
    }

    public void rest() {
        System.out.println("rest() called");
    }

    public void talk() {
        System.out.println("talk() called");
    }
}
