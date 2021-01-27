package validator.example;

import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ValidatorExampleApplication {
  public static void main(String[] args) {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

    Singer singer = new Singer();
    singer.setFirstName(null);
    singer.setLastName("Mayer");

    Validator singerValidator = ctx.getBean("singerValidator", Validator.class);

    BeanPropertyBindingResult result = new BeanPropertyBindingResult(singer, "John");

    ValidationUtils.invokeValidator(singerValidator, singer, result);

    List<ObjectError> errors = result.getAllErrors();

    System.out.println(errors);

    ctx.close();
  }

}
