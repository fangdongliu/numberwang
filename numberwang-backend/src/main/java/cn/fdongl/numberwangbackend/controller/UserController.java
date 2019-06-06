package cn.fdongl.numberwangbackend.controller;

import cn.fdongl.numberwangbackend.repository.UserRepository;
import cn.fdongl.numberwangbackend.security.AppUser;
import cn.fdongl.numberwangbackend.security.AppUserDetailServiceImpl;
import cn.fdongl.numberwangentity.entity.User;
import cn.fdongl.numberwangentity.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    AppUserDetailServiceImpl userService;

    @PostMapping("/register")
    @ResponseBody
    public Result register(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String mail){
        return userService.register(username, password, mail);
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
