package top.focksor.loginwithspringsecurity.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.focksor.loginwithspringsecurity.DAO.UserDAO;
import top.focksor.loginwithspringsecurity.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author focksor
 * @email focksor@outlook.com
 * @date 2019/9/26 19:40
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.getUserByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("not found");
        }
        logger.info("user: " + user.toString());
        String roles = user.getRoles();
        List<GrantedAuthority> authorities = new ArrayList<>();

        if(roles!=null && !"".equals(roles)) {
            String[] roleArray = roles.split(";");
            for(String role:roleArray) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }

        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                authorities
        );
    }

    public void addUser(User user) {
        userDAO.save(user);
    }

    public boolean isUser(String username) {
        User user = userDAO.getUserByUsername(username);
        return user!=null;
    }
}
