package com.codegym.cms.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class Logger {


    @AfterThrowing(pointcut = "testPointCut()" ,throwing = "e")
    public void log(JoinPoint joinPoint, Exception e) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println(String.format("[CMS] co loi xay ra: %s.%s%s: %s", className, method, args, e.getMessage()));
    }

    @Pointcut("execution(public * com.codegym.cms.service.book.IBookService.*(..))")
    public void testPointCut(){

    }

    @After("execution(public * com.codegym.cms.service.book.IBookService.out(..))")
    public void doMethodOutCheck(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println(String.format("Thao tac voi sach: %s.%s%s", className, method, args));
    }

    @After("execution(public * com.codegym.cms.service.book.IBookService.in(..))")
    public void doMethodInCheck(JoinPoint joinPoint){
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.println(String.format("Thao tac voi sach: %s.%s%s", className, method, args));
    }
}