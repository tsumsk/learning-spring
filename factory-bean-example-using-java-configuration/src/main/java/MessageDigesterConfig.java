import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageDigesterConfig {
    @Bean
    public MessageDigestFactoryBean shaDigest() {
        MessageDigestFactoryBean bean = new MessageDigestFactoryBean();
        bean.setAlgorithmName("SHA1");
        return bean;
    }

    @Bean
    public MessageDigestFactoryBean defaultDigest() {
        MessageDigestFactoryBean bean = new MessageDigestFactoryBean();
        return bean;
    }

    @Bean
    public MessageDigester digester() throws Exception {
        MessageDigester digester = new MessageDigester();
        digester.setDigest1(shaDigest().getObject());
        digester.setDigest2(defaultDigest().getObject());
        return digester;
    }
}
