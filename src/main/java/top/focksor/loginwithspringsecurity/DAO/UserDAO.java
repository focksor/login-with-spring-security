package top.focksor.loginwithspringsecurity.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import top.focksor.loginwithspringsecurity.pojo.User;

/**
 * @author focksor
 * @email focksor@outlook.com
 * @date 2019/9/26 19:01
 */

public interface UserDAO extends JpaRepository<User,String> {
    /**
     * 通过邮箱查找用户
     * @param email 用户的邮箱
     * @return User 邮箱与传入邮箱相等的用户
     */
    User getUserByEmail(String email);

    User getUserByUsername(String username);
}
