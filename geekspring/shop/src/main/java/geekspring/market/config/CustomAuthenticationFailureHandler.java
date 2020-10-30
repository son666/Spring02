package geekspring.market.config;

import geekspring.market.utils.RabbitProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private RabbitProvider rabbitProvider;

    @Autowired
    public void setRabbitProvider(RabbitProvider rabbitProvider) {
        this.rabbitProvider = rabbitProvider;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authentication) throws IOException, ServletException {
        String userName = request.getParameter("username");

        rabbitProvider.openConnect();
        rabbitProvider.sendMsg("Fail login userName: " + userName);
        response.sendRedirect(request.getContextPath() + "/login?error");
    }
}
