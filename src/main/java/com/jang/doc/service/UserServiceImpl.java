package com.jang.doc.service;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.jang.doc.dao.UserDao;
import com.jang.doc.model.User;

@Service("userService") 
public class UserServiceImpl implements UserService {
	

	//의존관계는 아버지 (부모) 인터페이스와 맺어야한다
		//1. xml 수동 2. 어노테이션 자동 방식 연결 
		
		//이방법1
		@Resource(name="userDao") //이는 이름에 대한 검색   의존관계 주입
		private UserDao userDao; //인터페이스와 의존관계설정!! 중요 (부모객체와) 외부에 찾아서 잡아 넣엇따 -> 리스코프 치환 법칙과 전략패턴을 쓰고 있다
		
		
		//이방법2 
		//@Autowired이것은 타입에 의한 검색  //or @Resource(name="userDao")
		//public void setUserDao(UserDao userDaImpl) {
			//이는 root-context의 name과 일치해야한다 set첫문자는 대문자
//			this.userDao = userDaImpl;
//		}//의존관계 주입
	
		@Override //받은 데이터를 전달만 한다 즉 받은 데이터의 경로는  userDao -> userDaoImpl ->이는 방법1에 의해 값을 달라한것-> 이 받은 값을 전달한다 
		public User getUser(String userId) { 
			// TODO Auto-generated method stub
			return userDao.getUser(userId);   //userDao로 전달 이는 다른 것들과는 다르게 입력한 값을 DB와 비교후에 다시 받아와야 하기 때문에 반환값이 있다 
			//이는 userDao에서 userid에 사용자가 입력한 정보를 가져온다.
			//그리고 return 그 값을 반환한다.
		}
	
	public void getUser1(String pass ,String id) {
		userDao.getUser1(pass,id);
	}// 탈퇴하기 위해서는 회원의 비밀번호를 요구하기 떄문에 아래 수정이나 삽입 , 삭제등은 값을 DB에 넘겨주기만 하면 되지만 
		// 이는 입력한 값을 DB에 넘겨주고 그 값과 비교하기 위해 값을 가져와야하기 때문에 

	@Override
	public void updateUser(User user) {  //이는 DB에 값을 넣는 것이기 때문에 반환값이 없다 .
		// TODO Auto-generated method stub
		userDao.updateUser(user);
	}
	
	

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		userDao.insertUser(user);
	}
	
	@Override
	public void deleteUser(User user, String userId, String password) {
		// TODO Auto-generated method stub}
		userDao.deleteUser(userId, password);

	}
	
	// 아이디 찾기
	@Override
	public User getfindId(String email, String phone) {
			return userDao.getfindId(email ,phone);
		}
	
	
	  @Override 
	  public User getfind_pw(String userid, String name) {
		 return userDao.getfind_pw(userid, name);
	  }
	
	  
	  
	/*
	 * @Override public User findId(HttpServletResponse response, String email)
	 * throws Exception { response.setContentType("text/html;charset=utf-8");
	 * PrintWriter out = response.getWriter(); User id = userDao.findId(email);
	 * 
	 * if (id == null) { out.println("<script>");
	 * out.println("alert('가입된 아이디가 없습니다.');"); out.println("history.go(-1);");
	 * out.println("</script>"); out.close(); return null; } else { return id; } }
	 */
	

}
