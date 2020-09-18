public class ContextualizedDependencyLookup implements ManagedComponent{

    private Dependency dependency;

    @Override
    public void performLookup(Container container) {
        this.dependency = (Dependency) container.getDependency("myDependency");
    }
}
