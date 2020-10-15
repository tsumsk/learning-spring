import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Locale;

public class I18nMessageSourceExampleApplication {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context.xml");
        ctx.refresh();

        Locale english = Locale.ENGLISH;
        Locale china = Locale.CHINA;

        System.out.println(ctx.getMessage("msg", null, english));
        System.out.println(ctx.getMessage("msg", null, china));

        System.out.println(ctx.getMessage("nameMsg", new Object[] {"John", "Mayer"}, english));
        System.out.println(ctx.getMessage("nameMsg", new Object[] {"刘", "旭"}, china));

        ctx.close();
    }
}
