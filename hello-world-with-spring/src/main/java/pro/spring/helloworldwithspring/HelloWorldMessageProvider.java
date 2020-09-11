package pro.spring.helloworldwithspring;

public class HelloWorldMessageProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello, World";
    }
}
