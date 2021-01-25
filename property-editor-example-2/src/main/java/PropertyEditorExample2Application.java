import org.springframework.context.support.GenericXmlApplicationContext;

public class PropertyEditorExample2Application {
    public static void main(String[] args) throws Exception {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context.xml");
        ctx.refresh();

        Singer eric = ctx.getBean("eric", Singer.class);
        System.out.println(eric);

        Singer countrySinger = ctx.getBean("countrySinger", Singer.class);
        System.out.println(countrySinger);

        ctx.close();
    }
}
