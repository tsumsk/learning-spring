public class ReplacementTarget {
    public String formatMessage(String message) {
        return "<h1>" + message + "</h1>";
    }

    public String formatMessage(Object message) {
        return "<h1>" + message + "</h1>";
    }
}
