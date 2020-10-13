import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class Config {
    @Lazy
    @Bean(initMethod = "afterPropertiesSet", destroyMethod = "destroy")
    DestructiveBean destructiveBean() {
        DestructiveBean bean = new DestructiveBean();
        bean.setFilePath("/tmp/test.txt");
        return bean;
    }
}
