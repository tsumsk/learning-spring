package bean.name.aware.example;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class Singer implements BeanNameAware {
    private String name;

    @Override
    public void setBeanName(String name) {
        this.name = name;
    }

    public void sing() {
        System.out.println("bean.name.aware.example.Singer " + name + " - sing()");
    }
}
