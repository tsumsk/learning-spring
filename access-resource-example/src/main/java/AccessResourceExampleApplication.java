import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class AccessResourceExampleApplication {
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext();

        Resource res1 = ctx.getResource("file://" +
            "/Users/tiger/liuxu/learning-spring/access-resource-example/src/main/resources/test.txt");
        displayInfo(res1);

        Resource res2 = ctx.getResource("classpath:test.txt");
        displayInfo(res2);

        Resource res3 = ctx.getResource("http://www.bing.com.cn");
        displayInfo(res3);

    }

    private static void displayInfo(Resource res) throws Exception {
        System.out.println(res.getClass());

        InputStream inputStream = res.getInputStream();
        List<String> lines = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
            .lines().collect(Collectors.toList());
        lines.stream().forEach(e -> System.out.println(e));
        System.out.println("");
    }
}
