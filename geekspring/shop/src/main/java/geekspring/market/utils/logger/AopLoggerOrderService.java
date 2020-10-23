package geekspring.market.utils.logger;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//Logger ShoppingCartService

@Aspect
@Component
public class AopLoggerOrderService {

    Logger marketLogger = Logger.getLogger("market");

    @Before("execution(public * geekspring.market.services.OrderService.makeOrder(..))")
    public void beforeMakeOrderInOrderService(JoinPoint joinPoint) {
        String  message = "В OrderService был вызван метод: makeOrder";
        marketLogger.info(message);
    }

    @After("execution(public * geekspring.market.services.OrderService.makeOrder(..))")
    public void afterMakeOrderInOrderService() {
       marketLogger.info("Был создан заказ");
    }

    @Before("execution(public * geekspring.market.services.OrderService.saveOrder(..))")
    public void beforeSaveOrderInOrderService(JoinPoint joinPoint) {
        String  message = "В OrderService был вызван метод: saveOrder";
        marketLogger.info(message);
    }

    @After("execution(public * geekspring.market.services.OrderService.saveOrder(..))")
    public void afterSaveOrderInOrderService() {
        marketLogger.info("Новый заказ сохранен в БД");
    }

}
