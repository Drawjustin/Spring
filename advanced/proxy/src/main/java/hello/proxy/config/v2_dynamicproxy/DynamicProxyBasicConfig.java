package hello.proxy.config.v2_dynamicproxy;

import hello.proxy.app.v1.*;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.config.v2_dynamicproxy.handler.LogTraceBasicHandler;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyBasicConfig {
    @Bean
    public OrderControllerV1 orderController(LogTrace trace){
        OrderControllerV1 orderController = new OrderControllerV1Impl(orderService(trace));
        OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(), new Class[]{OrderControllerV1.class}, new LogTraceBasicHandler(orderController, trace));
        return proxy;
    }
    @Bean
    public OrderServiceV1 orderService(LogTrace trace){
        OrderServiceV1 orderService = new OrderServiceV1Impl(orderRepositoryV1(trace));
        OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(), new Class[]{OrderServiceV1.class}, new LogTraceBasicHandler(orderService, trace));
        return proxy;
    }
    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace trace){
        OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();
        OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(), new Class[]{OrderRepositoryV1.class}, new LogTraceBasicHandler(orderRepository, trace));
        return proxy;
    }
}
