package com.atymtay.online_survey.aspect;

import com.atymtay.online_survey.entity.Users;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Aspect
public class AspectAnno {

    @Before("com.atymtay.online_survey.aspect.MyPointcut.allGetMethods()")
    public void beforeGetUsersData(){
        System.out.println("beforeGetUsersData: trying to get all data's...");
        System.out.println("<--------------------------------------------->");
    }



    @AfterReturning(
            pointcut = "com.atymtay.online_survey.aspect.MyPointcut.GetByIdMethods()",
            returning = "user"
    )
    public void afterReturningUserData( Optional<Users> user){

        System.out.println("afterReturningUserData: logging to get user data...");

        System.out.println("---------Taken User---------");
        System.out.println(user.get());

    }

    @AfterThrowing(
            pointcut = "com.atymtay.online_survey.aspect.MyPointcut.GetByIdMethods()",
            throwing = "exception"
    )
    public void afterThrowingAllGetMethodAdvice(Throwable exception){

        System.out.println("afterThrowingAllGetMethodAdvice:  logging for handling exception " +
                exception);
        System.out.println("-----------------------------------------------------");
    }


    @After("com.atymtay.online_survey.aspect.MyPointcut.allGetMethods()")
    public void afterGetMethods(){
        System.out.println("afterGetMethods: finishing the method call or handling the exception.");
        System.out.println("---------------------------------");
    }

    @Around("com.atymtay.online_survey.aspect.MyPointcut.allGetMethods()")
    public Object aroundGetMethodAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("aroundGetMethodAdvice: logging to get data...");
        Object obj = null;

        try {
            obj = proceedingJoinPoint.proceed();
            System.out.println(obj);
        } catch (Exception e){
            System.out.println("aroundGetMethodAdvice: handling exception...");
            throw e;
        }

        System.out.println("aroundGetMethodAdvice: succesfully finished execution!");

        return obj;
    }

}
