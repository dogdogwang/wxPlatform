package net.wjd.wx.user.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import net.wjd.wx.user.dao.UserDao;
import net.wjd.wx.user.model.User;

import org.springframework.stereotype.Service;


@Transactional
@Service
public class UserService {
	
	@Resource
	private UserDao userDao;
	
	public User getUser(String username) {
		return userDao.getUser(username);
	}
	
	public boolean addUser(User user){
		return userDao.addUser(user);
	}

}
