import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

public class MethodReplacementUsingXmlApplication {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context.xml");
        ctx.refresh();

        ReplacementTarget replacementTarget = ctx.getBean("replacementTarget", ReplacementTarget.class);
        ReplacementTarget standardTarget = ctx.getBean("standardTarget", ReplacementTarget.class);

        displayInfo(replacementTarget);
        displayInfo(standardTarget);

        ctx.close();
    }
    private static void displayInfo(ReplacementTarget target) {
        System.out.println(target.formatMessage("Thanks for playing, try again!"));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("perfTest");
        for (int x = 0; x < 1000000; x++) {
            String out = target.formatMessage("No filter in my head");
            //commented to not pollute the console
            //System.out.println(out);
        }
        stopWatch.stop();

        System.out.println("1000000 invocations took: " + stopWatch.getTotalTimeMillis() + " ms");
    }
}
