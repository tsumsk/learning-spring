import inject.collection.values.using.annotation.CollectionInjection;
import inject.collection.values.using.annotation.anotherexample.ArtworkManager;
import org.springframework.context.support.GenericXmlApplicationContext;

public class InjectCollectionValuesUsingAnnotationApplication {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context.xml");
        ctx.refresh();

        CollectionInjection instance = ctx.getBean("collectionInjection", CollectionInjection.class);
        instance.displayInfo();

        ArtworkManager artworkManager = ctx.getBean("artworkManager", ArtworkManager.class);
        artworkManager.send();

        ctx.close();
    }
}
