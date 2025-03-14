package hello.proxy.pureproxy.decorator.code;

public abstract class Decorator {
    protected Component component;

    public Decorator(Component component) {
        this.component = component;
    }
}
