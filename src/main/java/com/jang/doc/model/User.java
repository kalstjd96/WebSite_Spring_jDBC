package com.jang.doc.model;

import javax.validation.constraints.NotEmpty;

public class User {

	
		@NotEmpty(message="id를 입력하세요!") //NotEmpty는 그 칸이 비어있나 안 비어있나 체크하는 코드 
		private String id;
		
		@NotEmpty(message="password를 입력하세요!")
		private String pass;
		
		//@NotEmpty(message="password를 확인이 필요합니다!")
		private String pass2;
		
		@NotEmpty(message="이름을 입력하세요!")
		private String name;
		
		@NotEmpty(message="우편번호를 입력하세요!")
		private String zip;
		
		@NotEmpty(message="주소를 입력하세요!")
		private String addr1;
		
		@NotEmpty(message="상세주소를 입력하세요!")
		private String addr2;
		
		@NotEmpty(message="핸드폰번호를 입력하세요!")
		private String phone;
		
		@NotEmpty(message="이메일을 입력하세요!")
		private String email;
		
	//	@NotEmpty(message="주민번호를 입력하세요!")
	//	private String jumin;
		
		
		
	//	public String getJumin() {
	//		return jumin;
	//	}
	//	public void setJumin(String jumin) {
	//		this.jumin = jumin;
	//	}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPass() {
			return pass;
		}
		public void setPass(String pass) {
			this.pass = pass;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getZip() {
			return zip;
		}
		public void setZip(String zip) {
			this.zip = zip;
		}
		public String getAddr1() {
			return addr1;
		}
		public void setAddr1(String addr1) {
			this.addr1 = addr1;
		}
		public String getAddr2() {
			return addr2;
		}
		public void setAddr2(String addr2) {
			this.addr2 = addr2;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPass2() {
			return pass2;
		}
		public void setPass2(String pass2) {
			this.pass2 = pass2;
		}
		
		
}
