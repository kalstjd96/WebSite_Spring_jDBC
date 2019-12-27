package com.jang.doc.dao;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.*;

import com.jang.doc.model.User;


public interface UserDao {

	User getUser(String userId);  
	//회원탈퇴하기 위해서는 비번을 입력해야 하기에 비번값을 가져온다
	User getfindId(String email, String phone);// 아이디를 찾기위해 이메일을 받아온다 
	User getfind_pw(String userid, String name);
	//비밀번호를 찾기위해서 아이디값과 이름값을 가져온다.
	//그값을 User로 전부 넘겨주기 위해 User로 한것 
	
	void updateUser(User user);
	void insertUser(User user);
	void deleteUser(String userId, String password);
	void getUser1(String pass,String id); 
	//void setUserDao(UserDao userDaoImpl);
}
