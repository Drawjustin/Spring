package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {

    @Test
    void begin_end() {
        HelloTraceV2 traceV2 = new HelloTraceV2();
        TraceStatus status = traceV2.begin("hello");
        TraceStatus status2 = traceV2.beginSync(status.getTraceId(), "hello2");
        traceV2.end(status2);
        traceV2.end(status);
    }


    @Test
    void exception() {
        HelloTraceV2 traceV2 = new HelloTraceV2();
        TraceStatus status = traceV2.begin("hello");
        TraceStatus status2 = traceV2.beginSync(status.getTraceId(), "hello2");
        traceV2.exception(status2,new IllegalStateException());
        traceV2.exception(status,new IllegalStateException());

    }
}