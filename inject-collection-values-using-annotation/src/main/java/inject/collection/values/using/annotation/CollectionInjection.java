package inject.collection.values.using.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Service("collectionInjection")
public class CollectionInjection {
    // all beans of type Map<String, Object> will be injected
    @Autowired
    private Map<String, Object> map;

    @Resource(name = "set")
    private Set set;

    @Resource(name = "list")
    private List list;

    @Resource(name = "props")
    private Properties props;

    public void displayInfo() {
        System.out.println("Map contents:\n");
        map.entrySet().stream().forEach(e -> System.out.println("Key: " + e.getKey() + " - Value: " + e.getValue()));

        System.out.println("\nSet contents:\n");
        set.forEach(e -> System.out.println("Value: " + e));

        System.out.println("\nList contents:\n");
        list.forEach(e -> System.out.println("Value: " + e));

        System.out.println("\nProperties contents:\n");
        props.entrySet().stream().forEach(e -> System.out.println("Key: " + e.getKey() + " - Value: " + e.getValue()));
    }
}
