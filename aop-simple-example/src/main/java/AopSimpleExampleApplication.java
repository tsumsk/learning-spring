import org.springframework.aop.framework.ProxyFactory;

public class AopSimpleExampleApplication {
    public static void main(String[] args) {
        Agent agent = new Agent();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(agent);
        proxyFactory.addAdvice(new AgentDecorator());
        Agent proxy = (Agent)proxyFactory.getProxy();

        agent.speak();
        System.out.println("");
        proxy.speak();
    }
}
