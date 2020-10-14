import org.apache.commons.codec.binary.Hex;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.security.MessageDigest;

public class FactoryBeanExampleApplication {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context.xml");
        ctx.refresh();

        MessageDigester digester = ctx.getBean("digester", MessageDigester.class);
        digester.digest("Hello World!");

        MessageDigestFactoryBean factoryBean = ctx.getBean("&shaDigest", MessageDigestFactoryBean.class);
        try {
            MessageDigest shaDigest = factoryBean.getObject();
            System.out.println(Hex.encodeHexString(shaDigest.digest("Hello World!".getBytes())));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ctx.close();
    }
}
