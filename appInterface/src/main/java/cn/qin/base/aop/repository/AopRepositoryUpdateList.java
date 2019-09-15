package cn.qin.base.aop.repository;

import cn.qin.constancts.FrameworkConstants;
import cn.qin.util.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author qiaomengnan
 * @ClassName: AopRepositoryUpdateList
 * @Description:
 * @date 2018/2/26
 */
@Component
@Aspect
public class AopRepositoryUpdateList {

    @Pointcut(FrameworkConstants.AOP_REPOSITORY_UPDATE_LIST)
    public void aspect(){}

    @Around("aspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Object [] args = joinPoint.getArgs();
        if(ArrayUtils.isNotNullAndLengthNotZero(args))
            for(Object arg : args)
                AopRepositoryUtil.updateList(arg);
        Object result = joinPoint.proceed();
        return result;
    }

}
