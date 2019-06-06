package cn.fdongl.numberwangbackend.security;

import cn.fdongl.numberwangbackend.repository.UserRepository;
import cn.fdongl.numberwangentity.entity.User;
import cn.fdongl.numberwangentity.result.FailMsg;
import cn.fdongl.numberwangentity.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author LiuFangdong
 */
@Service
@Slf4j
public class AppUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException(String.format("未发现目标用户：%s",username));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        AppUser appUser = new AppUser();
        appUser.setUser(user);
        appUser.setAuthorities(authorities);
        return appUser;
    }

    public Result register(String username,String password,String mail){
        if (username.length()>100||password.length()>100||mail.length()>100){
            return Result.fail(FailMsg.PARAM_TOO_LONG);
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setMail(mail);
        user.setCreateDate(new Date());
        userRepository.save(user);
        log.info(user.getId()+"");
        return Result.success();
    }

}
