package spring.http.invoker.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import spring.http.invoker.example.SingerService;

@Configuration
public class HttpInvokerConfig {

  @Autowired
  private SingerService singerService;

  public HttpInvokerServiceExporter httpInvokerServiceExporter() {
    HttpInvokerServiceExporter invokerService = new HttpInvokerServiceExporter();
    invokerService.setService(singerService);
    invokerService.setServiceInterface(SingerService.class);

    return invokerService;
  }

}
