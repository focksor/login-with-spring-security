package top.focksor.loginwithspringsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.focksor.loginwithspringsecurity.pojo.User;
import top.focksor.loginwithspringsecurity.service.UserDetailsServiceImpl;

/**
 * @author focksor
 * @email focksor@outlook.com
 * @date 2019/10/14 20:46
 */

@Controller
public class WebController {
    private UserDetailsServiceImpl userService;

    @Autowired
    public void setUserService(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping("login")
    public String login() {
        createTestAccount();

        return "login";
    }

    @RequestMapping("/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String userName = authentication.getName();
            model.addAttribute("userName", userName);
        }
        return "index";
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

    @ResponseBody
    @RequestMapping("/userAndAdmin")
    public String userAndAdmin() {
        return "userAndAdmin<br><br><a href=\"/logout\">logout</a>";
    }

    public void createTestAccount() {
        // Create Test Account
        if(!userService.isUser("admin")) {
            User user = new User();
            user.setEmail("focksor@outlook.com");
            user.setUsername("admin");
            user.setPassword("admin");
            user.setEnabled(true);
            user.setRoles("ROLE_ADMIN");

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
    }
}
