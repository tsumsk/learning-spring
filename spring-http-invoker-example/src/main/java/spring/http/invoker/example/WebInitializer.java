package spring.http.invoker.example;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import spring.http.invoker.example.config.DataServiceConfig;
import spring.http.invoker.example.config.HttpInvokerConfig;
import spring.http.invoker.example.config.WebConfig;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[] {
        DataServiceConfig.class
    };
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[] {
        HttpInvokerConfig.class, WebConfig.class
    };
  }

  @Override
  protected String[] getServletMappings() {
    return new String[] {"/invoker/*"};
  }
}
