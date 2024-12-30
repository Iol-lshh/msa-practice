package lshh.core.lib.util;

public class TraceThreadManager {
    private static final ThreadLocal<Object> USER_ID = new ThreadLocal<>();
    private static final ThreadLocal<Object> TRACE_ID = new ThreadLocal<>();
    private static final ThreadLocal<Object> SPAN_ID = new ThreadLocal<>();

    public static <T> T threadUserId(Class<T> type){
        return (T) USER_ID.get();
    }
    public static <T> void threadUserId(T userId){
        USER_ID.set(userId);
    }
    public static <T> T threadTraceId(Class<T> type){
        return (T) TRACE_ID.get();
    }
    public static <T> void threadTraceId(T traceId){
        TRACE_ID.set(traceId);
    }
    public static <T> T threadSpanId(Class<T> type){
        return (T) SPAN_ID.get();
    }
    public static <T> void threadSpanId(T spanId){
        SPAN_ID.set(spanId);
    }
}
