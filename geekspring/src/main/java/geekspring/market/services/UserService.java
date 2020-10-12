package geekspring.market.services;

import geekspring.market.entites.SystemUser;
import geekspring.market.entites.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findByUserName(String username);
    boolean save(SystemUser systemUser);
}
