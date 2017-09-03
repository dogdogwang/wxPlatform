package net.wjd.wx.user.controller;

import javax.annotation.Resource;

import net.wjd.wx.user.model.User;
import net.wjd.wx.user.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/register")
	public String register(String username,String password){
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		userService.addUser(user);
		return "index";
	}
	
	@RequestMapping("/login")
	public void login(String username,String password){
		System.out.println("------------------");
	}
	
	@RequestMapping("/getUser")
	@ResponseBody
	public void getUser(String username){
		User user = userService.getUser(username);
		System.out.println("------------------"+user.getUsername());
	}

}
