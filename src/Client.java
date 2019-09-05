import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.util.Date;

public class Client {

    @Test
    public void clientOfProxy() {
        Reminder reminder = new ReminderProxy(new ReminderImpl());
        reminder.remind();
    }

    @Test
    public void clientOfDynamicProxy() {
        // Proxy.newProxyInstance参数:
        // 1. ClassLoader: 定义代理类的类加载器
        // 2. Class[]: 接口类的列表
        // 3. InvocationHandler: 调用方法的处理器
        Reminder reminder = (Reminder) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(),
                new Class[]{Reminder.class}, new DynamicProxy(new ReminderImpl()));
        reminder.remind();
    }

    private static class ReminderBeforeAdvice implements BeforeAdvice {
        @Override
        public void before() {
            System.out.println("Authorization: OK.");
        }
    }

    private static class ReminderAfterAdvice implements AfterAdvice {
        @Override
        public void after() {
            System.out.println("Log info: Remind at " + new Date().getTime());
        }
    }

    @Test
    public void clientOfProxyFactory() {
        Reminder reminder = (Reminder)new ProxyFactory(
                new ReminderBeforeAdvice(),
                new ReminderImpl(),
                new ReminderAfterAdvice()
        ).createProxy();
        reminder.remind();
    }
}