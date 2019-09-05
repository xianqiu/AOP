import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

public class DynamicProxy implements InvocationHandler {

    private Reminder reminder;

    public DynamicProxy(Reminder reminder) {
        this.reminder = reminder;
    }

    private void authorize() {
        System.out.println("Authorization: OK.");
    }

    private void log() {
        System.out.println("Log info: Remind at " + new Date().getTime());
    }

    /**
     *
     * @param proxy 代理对象
     * @param method 被调用的方法
     * @param args  被调用方法的参数
     * @return 被调用方法的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        authorize();
        Object result = null;
        result = method.invoke(reminder, args);
        log();
        return result;
    }
}
