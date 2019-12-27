package com.jang.doc.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jang.doc.model.User;
import com.jang.doc.service.UserService;

@Controller
public class LoginController {
	
		@Resource(name ="userService") //@Autowired  @Resource는 이름을 가지고 넣는 것  2.의존관계 주입 시험 !!! 
		    //root_context.xml에 연결되어있는 userService를 가져다 쓰겠다는 것 
		private UserService userService; //1.userService와 의존관계 설정하는 것 아버지 객체와 실행 순서외우기
		
		@RequestMapping(value="/login", method = RequestMethod.GET) //아레와 같은 value지만 들어오는 method방식에 따라 결정 url을 입력해서 들어온것 데이터가 1,2개 정도만 들어올때
		public String gotoLoginView(Model model) {					//page88  1번이 get 3번이 post  model 가방을만들고 user
		model.addAttribute("user", new User()); //빈 가방을 User을 만든다 그의 이름은 user 이를 loginForm으로 보낸다.
		return "loginForm"; // loginForm으로 가는데 DTO model가방을 가져가라
		}
		
		@RequestMapping(value="/login", method= RequestMethod.POST) //post방식으로 들어올때 여러개의 데이터가 들어올때
		public String onSubmit(@ModelAttribute @Validated User user, BindingResult result, Model model , HttpSession session) {
				//데이터를 한방에 담겠다 어디다?	DTO 객체 user에다 데이터를 넣겠다  그리고 빈자리가 있는지 검사해라 Valid 없다면 그리고 result에 담아라  
				//httpsession를 입력 파라메터에 추가해 준것 
		if(result.hasFieldErrors("id")|| result.hasFieldErrors("pass")) {
		model.addAllAttributes(result.getModel());
		return "loginForm";
		
		}
		
		try { // 이부분 중요 에러가 발생하면 catch부분이 실행  즉 입력한 id값이 존재 하지 않을때 catch문이 실행된다.
			User loginUser = this.userService.getUser(user.getId()); //model 부분에서 User.java에서 user의 Id값을 가져와라 DTO 객체를 loginUser에 넣어준다.
				//그릇 loginUser을 만들고 그 안에 userService그릇을 넣는다는 개념 
				//userId 가지고 user의 정보를 가져오라는 것 
			// user.getId값을 가져오라 -> userDao -> jdbcTempl -> userServuce ->loginUser 
			
			if(user.getPass().equals(loginUser.getPass())) { //user에 저장 되어있는 password와 loginUser가 직접입력한 password가 일치하는 지 확인 equals 
					model.addAttribute("loginUser", loginUser); 
				
				session.setAttribute("userId", loginUser.getId()); //session에 변수를 등록한다.
				session.setAttribute("userName", loginUser.getName());
				
				return "loginSuccess";
			}
			else {
			model.addAttribute("errMsg", "다시 시도해 주시기 바랍니다.");
			return "loginForm";
		}
		}
		catch(EmptyResultDataAccessException e) {   //결과가 없을 때 즉 입력한 정보가 DB에 있지 않을 때 이 부분이 실행한다
			model.addAttribute("errMsg", "오류가 발생하였습니다 다시 시도해주시기 바랍니다."); 
			return "loginForm";
		}

}
	

	
		//회원 가입  코드
		@RequestMapping(value="/join.do", method=RequestMethod.GET) //회원가입 클릭시(join.do를 누르면 /입력이되면) 회원가입 Form을 띄어주는 것 
		public String/*출력*/ toUserEntryView/*이름 */(Model/*입력 */ model) { //메소드 구성에 필요한 3가지 중요
			model.addAttribute("user", new User());
			return "joinForm";
			
		
		}
		//아이디 중복체크하는 코드
		@RequestMapping(value = "/checkid.do", method = RequestMethod.GET)
		//아이디 중복검사를 입력하면 실행 
		public String dupCheckId(@RequestParam("userId") String userId, Model model) {
			
			String message = "";
			int reDiv =0 ; 
			try {
				User loginUser = this.userService.getUser(userId);
				message = "이미 사용중인 아이디";
				reDiv =0;  //0이면 쓸수 없다 즉 에러가 없다면 쓸수 없다는 얘기
				userId=""; //id값을 비운다
			}catch (EmptyResultDataAccessException e) {
				message ="사용 가능한 아이디";
				reDiv =1;  //1이면 사용할수 있다
			}
			model.addAttribute("user", new User());
			model.addAttribute("message", message);
			model.addAttribute("reDiv", reDiv);
			model.addAttribute("userId", userId);  //즉 위에서 본 코드처럼 reDiv일 경우 userId값이 null값 비워진다.
			return "joinForm";

		}
		
		
		@RequestMapping(value = "/join.do", method = RequestMethod.POST) //이부분은 회원가입이 완료되게 하는 코드이다.
		public String dupCheckId(@Valid User user, BindingResult result,  Model model) throws Exception {
								//DTO객체에 비어있는 지 유효성검사를 실행 ㅡ 
			if(result.hasErrors()) {
				model.addAttribute(result.getModel()); //값이 비어 있다면 joinForm으로 전달
				return "joinForm";
			}
			
			try {  //데이터가 정상적으로 들어왔다면  실행 
				this.userService.insertUser(user);
				model.addAttribute("message", "다음과 같이 회원가입이 완료");
				model.addAttribute("user", user);
				return "joinSuccess"; //회원가입이 성공하였다면 joinSuccess 이동 
				
			}catch (DataIntegrityViolationException e) {
				model.addAttribute("esgMsg", "회원 가입이 안되었습니다 다시 시도해 주세요^^");	
				return "joinForm"; //회원가입이 실패되었다면 joinForm으로 이동 
			}
		}
	
	
		@RequestMapping(value="/edit", method =RequestMethod.GET)
		//요청을 연결한다 	     1번과     아래 코드들간의 관계를 연결하겠다는 것 이다.
		public String toUserEditView(@RequestParam("userId") String userId , Model model) {
								// 이는 요청에서 userId 값하나씩 읽을 때 쓰는 것 으로 우리가 아는 
								// String userId = request.getParameter("userId")와 같은 의미를 지닌다.
	//  접근지정자  출력         이름                           입   (여러개(modelAttribute)가 아닌 userId값을 하나 읽어서 그안에 값을 userId에 넣어라 어떤형태?? String형태 ,model은 빈가방을 만들어 보낸다		
												//modelAttribute은 여러개의 값을 읽는 어노테이션이다.
			//DB에 접근할 때에는 반드시 try catch문을 활용한다.	
			try {
				User loginUser = this.userService.getUser(userId); 
				model.addAttribute("user", loginUser);
				return "editForm";
			}
			catch (EmptyResultDataAccessException e) {
				model.addAttribute("user", new User() );
				model.addAttribute("errMsg", "시용자가 등록되어 있지 않습니다. ^^");
				return "loginform";
			}
		}
		
		@RequestMapping(value="/edit", method =RequestMethod.POST)
		public String onSumbit(@Valid User user, BindingResult result , Model model) throws Exception {
			
			if(result.hasErrors()) {
				System.out.println("오류 if");
				model.addAllAttributes(result.getModel());
				return "editForm";
			}
			
			try {
				System.out.println("오류 try");
				this.userService.updateUser(user);
				model.addAttribute("message", "다음과 같이 사용자 정보를 수정하였습니다. ");
				model.addAttribute("user", user);
				return "joinSuccess";
			}
			catch (DataAccessException e) {
				model.addAttribute("errMsg", "사용자 정보 수정에 실패하였습니다.^^");
				return "editForm";
			}
		}


	
	
	
	/*
	 * @Controller public class DeleteController {
	 * 
	 * @Resource(name ="userService") private UserService userService;
	 * 
	 * @RequestMapping(value="/delete", method = RequestMethod.GET) public String
	 * gotoLoginView(Model model) { model.addAttribute("user", new User()); return
	 * "deleteform"; }
	 * 
	 * @RequestMapping(value="/delete", method= RequestMethod.POST) public String
	 * onSubmit(@ModelAttribute @Validated User user, BindingResult result, Model
	 * model , HttpSession session) {
	 * 
	 * if(result.hasFieldErrors("pass")) {
	 * model.addAllAttributes(result.getModel()); return "deleteform";
	 * 
	 * }
	 * 
	 * try { User loginUser = this.userService.getUser1(user.getPass());
	 * 
	 * if(user.getPass().equals(loginUser.getPass())) {
	 * model.addAttribute("loginUser", loginUser);
	 * 
	 * session.invalidate();
	 * 
	 * return "loginForm"; } else { model.addAttribute("errMsg",
	 * "비밀번호가 일치하지 않습니다."); return "deleteform"; } }
	 * catch(EmptyResultDataAccessException e) { model.addAttribute("errMsg",
	 * "not exist."); return "deleteform"; }
	 * 
	 * }
	 * 
	  }*/

		 // 아이디 찾기 폼
		 //value값을 입력하면 findId_form 페이지가 열린다.
		 @RequestMapping(value = "/findId_form.do" , method = RequestMethod.GET)
		 public String findId_form(Model model) {
			 model.addAttribute("users", new User());
			 return "findId_form"; 
			 }
		
			
			// 아이디 찾기
			@RequestMapping(value = "/find_id.do", method = RequestMethod.POST)
	public String findId(@ModelAttribute @Validated User user, BindingResult result,
			Model model /* , @RequestParam("email") String email */) throws Exception{
	
				//데이터를 한방에 담겠다 어디다?	DTO 객체 user에다 데이터를 넣겠다  그리고 빈자리가 있는지 검사해라 Valid 없다면 그리고 result에 담아라  
				//httpsession를 입력 파라메터에 추가해 준것 
				//model은 가방 즉 view쪽에 가방을 넘겨서 그 값을 출력할 수 있게 하기 위한 것 
				
				// 이는 요청에서 userId 값하나씩 읽을 때 쓰는 것 으로 우리가 아는 
				// String userId = request.getParameter("userId")와 같은 의미를 지닌다.
//  접근지정자  출력         이름                           입   (여러개(modelAttribute)가 아닌 userId값을 하나 읽어서 그안에 값을 userId에 넣어라 어떤형태?? String형태 ,model은 빈가방을 만들어 보낸다		
								//modelAttribute은 여러개의 값을 읽는 어노테이션이다.
				//DB에 접근할 때에는 반드시 try catch문을 활용한다.	 쓰는 이유는 DB값이 없을 때의 오류가 아닌 오류출력값을 빼내기 위한 것 
				//DTO객체에 비어있는 지 유효성검사를 실행 ㅡ 
			try { 
				User loginUser = this.userService.getfindId(user.getEmail(), user.getPhone());
				//이메일과 폰번호로 아이디를 찾는 것이다.
				//userService에 있는 이메일과 폰번호를 가져오겠다 어디에 있는?? user 즉 DTO user에서 값을 userService에서 받아 여기로 전달한것
				
				if(user.getEmail().equals(loginUser.getEmail()) &&  //입력한 이메일과 폰번호가 일치한다면 아래를 실행한다
						user.getPhone().equals(loginUser.getPhone()))   
					 //조건문 user에서 가져온 DB값과 내가 입력한 loginUser값이 같은 지 확인 
					// 이메일과 폰번호가 일치하는지 확인하는것이다.
					
					model.addAttribute("id", loginUser.getId());
					//이는 가방에 id라는 이름을 만들엇꼬 여기에 내가 입력한 id값을 넣는것
					model.addAttribute("message", "비밀번호를 모르시다면 비밀번호 찾기로~");
					model.addAttribute("id", loginUser.getId());
					return "find_id";
					//반환 즉 find_id 폼으로 페이지를 넘기는 것이다.
																										
			}
			catch(EmptyResultDataAccessException e) {  
				//이 부분은 DB갑이 존재하지 않을때나 값이 존재하지 않을 때 실행되는 부분을 나타낸다.
				model.addAttribute("errMsg", "이메일을 다시 입력해 주세요");   
				model.addAttribute("errMsg2", "-을 제외한 폰번호를 다시 입력하여 주세요");   
				
				return "findId_form";
				//반환 findId_form 즉 제자리
			}


	}
	
		
		
		//비밀번호 찾기를 나타내는 곳 
		  @RequestMapping(value = "/find_pw_form.do", method = RequestMethod.GET) 
		  public String find_pw_form(Model model)
		 { 
			  //model.addAttribute("user", new User());
			  //user라는 model가방을 find_pw_form 페이지로 이동하면서 가방을 전달한다.
			  //DTO 객체에 값을 넘기는 역할을 한다.
			  return "find_pw_form"; }
		  		  // 비밀번호 찾기
		
		  
		  @RequestMapping(value = "/find_pw.do", method = RequestMethod.POST) 
	public String find_pw(@ModelAttribute @Validated User user, BindingResult result,
			Model model) throws Exception{
					
			
				try { 
					
					
					User loginUser = this.userService.getfind_pw(user.getId(), user.getName()); 				
					if(user.getId().equals(loginUser.getId()) &&
							user.getName().equals(loginUser.getName())) { //입력한 값과 DB값의 ID 비교
						//아이디와 이름을 가지고 비밀번호를 찾는다.
						
						//임시 비밀번호를 생성하는 코드
						String pass ="";
						//문자열 로 pass를 초기화 
						for(int i =0; i <10; i++) {
							pass += (char) ((Math.random() * 26) +97);
									//문자이기 때문에 char  
						}
						//for문을 이용하여 10자리의 임의값을 랜덤으로 생성한다.
						
						user.setPass(pass);  //임의로 변경된 비밀번호를  DTO user의 pass를 전달하여 변경한다.
											 //이는 DTO객체에 있는 user에 pass에 값을 보냄으로써 임의의 값을 보낸다는 것
						
						//비번 변경
						String id = loginUser.getId();
						//네가 입력한 id값을 id라고 선언한 변수에 넣는다.
						model.addAttribute("UserId", id); //UserId라는 가방안에 내가 입력한 id값을 넣는다 
						model.addAttribute("newPwd",pass); //newPwd라는 가방안에  임의로 생성된 비번값을 넣는다.
						System.out.println(id); //이 부분은 테스트 해보기 위함
						
						
						this.userService.getUser1(pass,id);//userService에 getUser1 pass,id를 호출
						//어디서 가져오나?? service -> dao -> jdbcTemplate 가서 DB값을 실행후 그 값을 가져온다
						
						
						return "find_pw";
						//반환한다 fid_pw로 
					
					}else{
						model.addAttribute("errMsg", "입력 정보가 일치하지 않습니다.");
						return "find_pw_form";
					}
					
					
				}catch(EmptyResultDataAccessException e) {   //이 부분은 sql문의 오류가 아닌 실제 데이터가 없을 때 출력하게 하는 것 
					model.addAttribute("errMsg", "입력정보가 올바르지 않습니다..");  
					return "find_pw_form";
				}
				
				
		  }
		
	
	
		  
		  
	@RequestMapping("/logout.do")
	public String logout(HttpSession session, Model model) {
		session.invalidate();
		model.addAttribute("msg", "정상적으로 로그아웃!!!");
		
		return "logout";  //redirect 로 하게 되면 다음에 오는게 jsp파일명이 아닌 url로 전달한다
									// 즉 view 이름이 아닌 직접 url을 입력한 꼴이 된다는 것이다.
	}
	
	
	
	

}

