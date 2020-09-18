import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionUsingAnnotationV1Application {
	public static void main(String[] args) {
	    ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloWorldConfig.class);

	    MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
	    mr.render();
	}
}
