import java.util.Properties;

public class MessageSupportFactory {
    private static MessageSupportFactory instance;

    private Properties properties;
    private MessageProvider provider;
    private MessageRenderer renderer;

    private MessageSupportFactory() {
        properties = new Properties();

        try {
            properties.load(this.getClass().getResourceAsStream("/msf.properties"));

            String rendererClass = properties.getProperty("renderer.class");
            String providerClass = properties.getProperty("provider.class");

            renderer = (MessageRenderer) Class.forName(rendererClass).newInstance();
            provider = (MessageProvider) Class.forName(providerClass).newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static {
        instance = new MessageSupportFactory();
    }

    public static MessageSupportFactory getInstance() {
        return instance;
    }

    public MessageProvider getMessageProvider() {
        return provider;
    }

    public MessageRenderer getMessageRenderer() {
        return renderer;
    }
}
