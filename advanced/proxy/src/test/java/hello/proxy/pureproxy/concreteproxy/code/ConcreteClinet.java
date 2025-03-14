package hello.proxy.pureproxy.concreteproxy.code;

import hello.proxy.pureproxy.concreteproxy.code.ConcreteLogic;

public class ConcreteClinet {
    private ConcreteLogic concreteLogic;

    public ConcreteClinet(ConcreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }
    public void execute(){
        concreteLogic.operation();
    }
}
