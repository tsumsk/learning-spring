package aop.declarative.config.using.aspectj.style.annotation.and.spring.boot.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Documentarist {
    private GrammyGuitarist grammyGuitarist;

    @Autowired
    public void setGrammyGuitarist(GrammyGuitarist grammyGuitarist) {
        this.grammyGuitarist = grammyGuitarist;
    }

    public void execute() {
        grammyGuitarist.sing();
        Guitar guitar = new Guitar();
        grammyGuitarist.sing(guitar);
        guitar.setBrand("Gibson");
        grammyGuitarist.sing(guitar);
        grammyGuitarist.talk();
    }
}
