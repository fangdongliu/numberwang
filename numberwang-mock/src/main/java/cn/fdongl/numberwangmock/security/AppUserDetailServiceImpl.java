package cn.fdongl.numberwangmock.security;

import cn.fdongl.numberwangentity.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiuFangdong
 */
@Service
public class AppUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(!username.equals("admin")){
            throw new UsernameNotFoundException(String.format("未发现目标用户：%s",username));
        }

        User detail = new User();
        detail.setId(1L);
        detail.setUsername(username);
        detail.setPassword(passwordEncoder.encode("123456"));
        detail.setMail("894856599@qq.com");

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        AppUser appUser = new AppUser();
        appUser.setUser(detail);
        appUser.setAuthorities(authorities);
        return appUser;
    }
}
