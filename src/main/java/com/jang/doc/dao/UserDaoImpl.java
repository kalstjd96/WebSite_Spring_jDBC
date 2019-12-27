package com.jang.doc.dao;

import java.io.PrintWriter;

import javax.mail.Session;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.jang.doc.model.User;
import com.jang.doc.service.UserService;

@Repository("userDao")  //이는 Servlet.xml에 저장소userDao를 만든다. beans을 자동 등록해준다 수동으로 할껏이기에 주석처리
public class UserDaoImpl implements UserDao {

	//이 사이는 JDBC를 처리과정을 대신해줄 JDBCTemplete클래스 사용을 위한 처리부분을 설정하는 공간이다 
	//반환은 service로 
	private JdbcTemplate jdbcTemplate; //뒤에 파란글씨는 객체이름 ,참조변수 만들어준다 JdbcTemplate형의 참조변수 
	private NamedParameterJdbcTemplate jdbcTemplate2; 
	
	@Autowired  //이는 DataSource타입의 객체를 Ioc컨테이너에 찾아 자동으로 연결해주는 것
	public void setDataSource(DataSource dataSource) {//의존관계주입 dataSource를 jdbcTemplate에 넣는 의존관계주입
		// name부분의 값을 setDataSource이렇게 정의    ref부분의 값 dataSource부분을 가져온다 이는 root-servlet.xml부분에 id부분으 ref으로 가져온것을 가져왔다
		//의존성을 주입받을 setter함수 생성 					//즉 이론적으로 이부분은 바꾸지 않아도되는 부분이 된다.(회사 모델 부분이 바뀌어도)
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.jdbcTemplate2 = new NamedParameterJdbcTemplate(dataSource);
		//위 2개의 차이점은 name은 이름을 찾아가는 것 그 위는 위치를 보고 찾아가는 것이다.
	}
	
	
	
	@Override //head는 상속 받고 body는 내가 생성하라  재정의 하는 곳이다 SLQ실행부분 으로 결과 값반환 처리를 담당 다형성
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		String GETUSER_ID = "select * from member_tbl where id = ?";
															//?는 sql 파라메터라고 부른다
		RowMapper<User> mapper = new BeanPropertyRowMapper<User>(User.class);
					//받아낼 그릇을 만든다(select문에 결과값을)				//UserDTO객체를 가져온다
		
		return this.jdbcTemplate.queryForObject(GETUSER_ID, mapper, userId);
		//return 문장의 진행순서 1. userId으로 확인후 -> 2. GETUSER_ID에 select 결과값 얻어 -> 3. mapper으로 받는다 후 return jdbcTemplate으로 
	}
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public void getUser1(String pass, String id) {
		
//	SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
	this.jdbcTemplate.update("UPDATE member_tbl SET pass=? WHERE id=?", pass, id);
		
	}
	
	
	
	

	 
	

	@Override 
	public void updateUser(User user) { 
		
		String SQL_UP= "UPDATE member_tbl SET pass=:pass, zip=:zip, addr1=:addr1, addr2=:addr2," + "phone=:phone, email=:email WHERE id= :id"; 
			 
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
		//이는 값이 한번에 이곳에 들어간다.
		this.jdbcTemplate2.update(SQL_UP, parameterSource);
		
	}

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		String SQL_INS = "INSERT INTO member_tbl (no, id, pass, name, zip, addr1, addr2,phone, email)"
				+ "VALUES(seq_no.nextval, :id, :pass , :name, :zip, :addr1, :addr2, :phone, :email)";
		
	
	SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
	//user는 바로 못 넣기 때문에 parameterSource를 만들어 넣어준다
	this.jdbcTemplate2.update(SQL_INS, parameterSource);
	}

	//아이디 찾기위해 설정한 코드로써 재상속을 한 코드이다
	@Override
	public User getfindId(String email, String phone) {
		
		String SQL_GETID = "SELECT id FROM member_tbl WHERE email=? and phone=?";
		
		RowMapper<User> mapper = new BeanPropertyRowMapper<User>(User.class);
		//위에 값을 받아낼 mapper 생성 = user , dto 객체를 반환되게 하기 위한 것이다
		return this.jdbcTemplate.queryForObject(SQL_GETID, mapper, email, phone);
	}
	
	
	 @Override public User getfind_pw(String userid, String name) {
	 String SQL_GETPASS = "select * from member_tbl where id = ? and name= ?";
	 
	 RowMapper<User> mapper = new BeanPropertyRowMapper<User>(User.class);
	  
	 return this.jdbcTemplate.queryForObject(SQL_GETPASS, mapper,userid, name);
	 
	 }


	
	@Override
	public void deleteUser(String userId, String password) {
		
//	SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
	this.jdbcTemplate.update("DELETE FROM member_tbl WHERE id = ? AND pass = ?", userId, password);
		
	}
	
	
	

}
