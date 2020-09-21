import bean.definition.using.annotation.v2.ConstructorConfusion;
import bean.definition.using.annotation.v2.MessageRenderer;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionUsingAnnotationV2Application {
	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
	    ctx.load("app-context.xml");
	    ctx.refresh();

	    MessageRenderer mr = ctx.getBean("renderer", MessageRenderer.class);
	    mr.render();

		ConstructorConfusion cc = ctx.getBean("constructorConfusion", ConstructorConfusion.class);
		System.out.println(cc);

	    ctx.close();
	}
}
