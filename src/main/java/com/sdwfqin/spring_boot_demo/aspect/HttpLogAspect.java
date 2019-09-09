package com.sdwfqin.spring_boot_demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 请求日志
 * <p>
 *
 * @author 张钦
 * @date 2019/9/9
 */
@Aspect
@Order(1)
@Component
public class HttpLogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截这个类的所有方法
     */
    @Pointcut("execution(public * com.sdwfqin.spring_boot_demo.web.*.*(..))")
    public void webLog() {
    }

    /**
     * 请求内容
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    }

    /**
     * 处理完请求，返回内容
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
    }
}
