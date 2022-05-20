package com.atymtay.online_survey.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class MyPointcut {

    @Pointcut("execution(public * com.atymtay.online_survey.service.impls.*.delete*(..))")
    public void getDeleteMethods(){}

    @Pointcut("execution(public * com.atymtay.online_survey.service.impls.*.update(..))")
    public void allUpdateMethods(){}

    @Pointcut("execution(public * com.atymtay.online_survey.service.impls.*.save*(..))")
    public void allAddMethods(){}

    @Pointcut("execution(public * com.atymtay.online_survey.service.impls.*.find*(..))")
    public void allGetMethods(){}

    @Pointcut("execution(public * com.atymtay.online_survey.service.impls.UserServiceImpl.findById(..))")
    public void GetByIdMethods(){}
}
