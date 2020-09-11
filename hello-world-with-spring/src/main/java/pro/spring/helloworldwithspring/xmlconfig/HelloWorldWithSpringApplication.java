package pro.spring.helloworldwithspring.xmlconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pro.spring.helloworldwithspring.MessageRenderer;

public class HelloWorldWithSpringApplication {
	public static void main(String[] args) {
	    ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");

	    MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
	    mr.render();
	}
}
