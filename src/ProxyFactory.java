import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    private BeforeAdvice beforeAdvice;
    private Object object;
    private AfterAdvice afterAdvice;

    public ProxyFactory(BeforeAdvice beforeAdvice, Object object, AfterAdvice afterAdvice) {
        this.beforeAdvice = beforeAdvice;
        this.object = object;
        this.afterAdvice = afterAdvice;
    }

    private InvocationHandler invocationHandler = new InvocationHandler() {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // 调用代理对象之前执行的方法
            if (beforeAdvice != null) beforeAdvice.before();
            // 调用被代理对象object的方法
            Object result = null;
            if (object != null) result = method.invoke(object, args);
            // 调用代理对象之后执行的方法
            if (afterAdvice != null) afterAdvice.after();
            return result;
        }
    };

    public Object createProxy() {
        return Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                object.getClass().getInterfaces(),
                invocationHandler
        );
    }
}
