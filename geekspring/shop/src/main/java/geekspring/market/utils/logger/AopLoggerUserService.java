package geekspring.market.utils.logger;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

//Logger ShoppingCartService

@Aspect
@Component
public class AopLoggerUserService {

    Logger marketLogger = Logger.getLogger("market");

    @Before("execution(public * geekspring.market.services.UserServiceImpl.*(..))")
    public void beforeAnyMethodInUserService(JoinPoint joinPoint) {
        String message;
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        message = "В UserServiceImpl был вызван метод: " + methodSignature;
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            message += ", Аргументы: ";
            for (Object o : args) {
                try {
                    message += o.toString() + ", ";
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
            }
        }
        marketLogger.info(message);
    }

}
