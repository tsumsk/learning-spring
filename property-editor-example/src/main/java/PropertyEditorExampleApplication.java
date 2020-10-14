import custom.CustomEditorExample;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.io.File;

public class PropertyEditorExampleApplication {
    public static void main(String[] args) throws Exception {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context.xml");
        ctx.refresh();

        PropertyEditorBean propertyEditorBean = ctx.getBean("builtInExample", PropertyEditorBean.class);

        CustomEditorExample customEditorExample = ctx.getBean("customEditorExample", CustomEditorExample.class);
        System.out.println(customEditorExample.getName());

        ctx.close();
    }
}
