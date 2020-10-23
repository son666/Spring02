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
public class AopLoggerShopingService {

    Logger marketLogger = Logger.getLogger("market");

    @Before("execution(public * geekspring.market.services.ShoppingCartService.*(..))")
    public void beforeAnyMethodInShopingService(JoinPoint joinPoint) {
        String message;
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        message = "В ShopingService был вызван метод: " + methodSignature;
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            message += ", Аргументы: ";
            for (Object o : args) {
                message += o.toString() + ", ";
            }
        }
        marketLogger.info(message);
    }

}
