package pro.spring.helloworldwithspring.annotationconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pro.spring.helloworldwithspring.MessageRenderer;

public class HelloWorldWithSpringApplication {
	public static void main(String[] args) {
	    ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfig.class);

	    MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
	    mr.render();
	}
}
