import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CollectionInjection {
    private Map<String, Object> map;
    private Set set;
    private List list;
    private Properties props;

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public void setList(List list) {
        this.list = list;
    }

    public void setProps(Properties props) {
        this.props = props;
    }

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
