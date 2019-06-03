package cn.fdongl.numberwangmock.controller;

import cn.fdongl.numberwangmock.security.AppUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError",true);
        return "login";
    }

    @RequestMapping("/data")
    @ResponseBody
    public Object cas(AppUser userDetail){
        return userDetail;
        //mapper.getListOne();
    }

}
