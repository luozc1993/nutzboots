package cn.luozc.oa.commom;

import org.nutz.aop.InterceptorChain;
import org.nutz.aop.MethodInterceptor;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.aop.SimpleAopMaker;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class DemoAopLoader extends SimpleAopMaker<SysLog> {

    public List<? extends MethodInterceptor> makeIt(SysLog sysLog, Method method, Ioc ioc) {
        return Arrays.asList(new SysLogMethodInterceptor());
    }
}

class SysLogMethodInterceptor implements MethodInterceptor {
    private static final Log log = Logs.get();

    public void filter(final InterceptorChain chain) throws Throwable {

        log.debug("hi");
        chain.doChain(); // 继续下一个拦截器, 如果要终止执行,不调用该方法即可
        log.debug("byte");
    }
}



