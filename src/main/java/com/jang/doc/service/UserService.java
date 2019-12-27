package com.jang.doc.service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jang.doc.model.User;

public interface UserService {

	User getUser(String userId);
	User getfindId(String email, String phone);
	User getfind_pw(String userid, String name); //비밀번호 찾기
	 /// FindController.java에 대한 곳에 값을 넘겨주는 것

	void updateUser(User user);
	void insertUser(User user);
	void deleteUser(User user, String userId, String password);
	void getUser1(String pass,String id);
	 
	}
