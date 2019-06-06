package cn.fdongl.numberwangmock.controller;

import cn.fdongl.numberwangentity.result.Result;
import cn.fdongl.numberwangmock.security.AppUser;
import cn.fdongl.numberwangmock.security.AppUserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
public class UserController {

    @Autowired
    AppUserDetailServiceImpl userService;

    @PostMapping("/register")
    @ResponseBody
    public Result register(String username, String password, String mail){
        return userService.register(username, password, mail);
    }

    @GetMapping("/user/info")
    @ResponseBody
    public Result info(AppUser user){
        user.getUser().setPassword("");
        return Result.success(user.getUser());
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError",true);
        return "login";
    }

}
