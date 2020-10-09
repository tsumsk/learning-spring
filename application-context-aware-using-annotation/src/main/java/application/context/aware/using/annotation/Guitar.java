package application.context.aware.using.annotation;

import org.springframework.stereotype.Component;

@Component("gopher")
public class Guitar {
    public void sing() {
        System.out.println("ABCDEFG");
    }
}
