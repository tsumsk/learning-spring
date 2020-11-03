import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Contact target() {
        Contact target = new Contact();
        target.setName("John Mayer");
        return target;
    }

    @Bean
    public Advisor advisor() {
        return new IsModifiedAdvisor();
    }

    @Bean
    public ProxyFactoryBean proxy() {
        ProxyFactoryBean proxy = new ProxyFactoryBean();
        proxy.setTarget(target());
        proxy.addAdvisor(advisor());
        proxy.setProxyTargetClass(true);

        return proxy;
    }
}
