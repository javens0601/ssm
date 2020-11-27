package com.javen.springaop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Date;

@Component
@Aspect
//@Slf4j
public class LogUtil {

    @Pointcut("execution(* com.javen.springaop.dao.*..*(..))")
    public void pointCut() {
        //声明一个切点，让其他的通知引用，重用性更强
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        String funname = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("方法名称：" + funname);
        System.out.println(Arrays.asList(args));
        System.out.println("方法执行前：" + new Date());
    }

    @After("pointCut()")
    public void after() {
        System.out.println("方法执行后：" + new Date());
    }

//    @AfterReturning("execution(* com.javen.springaop.dao.*..*(..))")
//    @AfterReturning("within(com.javen.springaop.dao..*)")
    //切点表达式的合并
    @AfterReturning(value = "@annotation(java.lang.Deprecated) && execution(* com.javen.springaop.dao.*..*(..))", returning = "returnValue")
    public void afterReturn(Object returnValue) {
        System.out.println("方法返回值：" + returnValue);
        System.out.println("方法返回后：" + new Date());
    }

    @AfterThrowing(value = "execution(* com.javen.springaop.dao.*..*(..))", throwing = "ex")
    public void afterThrow(Exception ex) {

        //打印异常栈信息
        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter, true));
        System.out.println(stringWriter.getBuffer().toString());

        System.out.println("方法异常后：" + new Date());
    }


    //环绕通知
    @Around(value = "pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println("环绕 方法名称：" + name);
        Object returnValue = null;
        try {
            System.out.println("环绕 前置通知");
            returnValue = joinPoint.proceed();
            System.out.println("环绕 后置通知");
        } catch (Throwable throwable) {
            System.out.println("环绕 异常通知");
            throwable.printStackTrace();
        } finally {
            System.out.println("环绕 返回通知");
        }
        return returnValue;
    }
}
