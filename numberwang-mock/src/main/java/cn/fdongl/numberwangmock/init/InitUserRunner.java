package cn.fdongl.numberwangmock.init;

import cn.fdongl.numberwangentity.entity.User;
import cn.fdongl.numberwangmock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class InitUserRunner implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setMail("894856599@qq.com");
        user.setCreateDate(new Date());
        userRepository.save(user);
    }
}
