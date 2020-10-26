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
public class AopLoggerRoleService {

    Logger marketLogger = Logger.getLogger("market");

    @Before("execution(public void geekspring.market.services.RoleService.saveRole(..))")
    public void beforeSaveRoleInRoleService(JoinPoint joinPoint) {
        String  message = "В RoleService был вызван метод: saveRole";
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            message += ", Аргументы: ";
            for (Object o : args) {
                message += o.toString() + ", ";
            }
        }
        marketLogger.info(message);
    }

    @Before("execution(public void geekspring.market.services.RoleService.deleteRoleById(..))")
    public void beforeDeleteRoleByIdInRoleService(JoinPoint joinPoint) {
        String  message = "В RoleService был вызван метод: deleteRoleById";
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
