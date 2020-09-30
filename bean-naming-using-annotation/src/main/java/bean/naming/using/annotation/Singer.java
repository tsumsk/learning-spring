package bean.naming.using.annotation;

import org.springframework.stereotype.Component;

@Component("johnMayer")
public class Singer {
    private String lyric = "We found a message in a bottle we were drinking";

    public void sing() {
        System.out.println(lyric);
    }
}
