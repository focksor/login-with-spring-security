package top.focksor.loginwithspringsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.focksor.loginwithspringsecurity.pojo.User;
import top.focksor.loginwithspringsecurity.service.UserServiceImpl;

/**
 * @author focksor
 * @email focksor@outlook.com
 * @date 2019/10/14 20:46
 */

@Controller
public class WebController {
    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping("login")
    public String login() {
        if(!userService.isUser("admin")) {
            User user = new User();
            user.setEmail("focksor@outlook.com");
            user.setUsername("admin");
            user.setPassword("admin");
            user.setEnabled(true);
            user.setRoles("ROLE_ADMIN;ROLE_USER");

            userService.addUser(user);
        }

        if(!userService.isUser("user")) {
            User user = new User();
            user.setEmail("focksor@outlook.com");
            user.setUsername("user");
            user.setPassword("user");
            user.setEnabled(true);
            user.setRoles("ROLE_USER");

            userService.addUser(user);
        }

        return "login";
    }

    @ResponseBody
    @RequestMapping("/")
    public String index() {
        return "index<br><br><a href=\"/logout\">logout</a>";
    }

    @ResponseBody
    @RequestMapping("/user")
    public String user() {
        return "user<br><br><a href=\"/logout\">logout</a>";
    }

    @ResponseBody
    @RequestMapping("/admin")
    public String admin() {
        return "admin<br><br><a href=\"/logout\">logout</a>";
    }
}
