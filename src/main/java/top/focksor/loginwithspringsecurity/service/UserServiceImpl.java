package top.focksor.loginwithspringsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.focksor.loginwithspringsecurity.DAO.UserDAO;
import top.focksor.loginwithspringsecurity.pojo.User;

/**
 * @author focksor
 * @email focksor@outlook.com
 * @date 2019/9/26 19:40
 */

@Service
public class UserServiceImpl implements UserDetailsService {
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

        return user;
    }

    public void addUser(User user) {
        userDAO.save(user);
    }

    public boolean isUser(String username) {
        User user = userDAO.getUserByUsername(username);
        return user!=null;
    }
}
