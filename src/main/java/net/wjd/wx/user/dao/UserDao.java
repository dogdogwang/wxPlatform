package net.wjd.wx.user.dao;

import net.wjd.wx.user.model.User;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public User getUser(String username) {
        return sqlSessionTemplate.selectOne(this.getClass().getName() + ".selectByUsername", username);
    } 
	
	public boolean addUser(User user){
		int count =  sqlSessionTemplate.insert(this.getClass().getName() + ".addUser", user);
		return count == 1;
	}
		

}
