package top.focksor.loginwithspringsecurity.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import top.focksor.loginwithspringsecurity.pojo.User;
import top.focksor.loginwithspringsecurity.service.UserServiceImpl;

import java.util.Collection;

/**
 * @author focksor
 * @email focksor@outlook.com
 * @date 2019/10/14 19:55
 */

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {
    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        User user = (User) userService.loadUserByUsername(username);
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
