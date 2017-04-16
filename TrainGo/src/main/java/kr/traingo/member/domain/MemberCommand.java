package kr.traingo.member.domain;

import java.sql.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class MemberCommand {
	@NotEmpty
	private String id;
	@Pattern(regexp="^[a-zA-Z0-9]*.{8,20}$")
	private String passwd;
	@NotEmpty
	private String name;
	@NotEmpty
	private String phone;
	@Email
	@NotEmpty
	private String email;
	@Size(min=5, max=5)
	private String zipcode;
	@NotEmpty
	private String address1;
	@NotEmpty
	private String address2;
	private int	lev;
	private Date reg_date;
	
	// 비밀번호 일치 여부 체크
	public boolean isCheckedPasswd(String userPasswd){
		if(passwd.equals(userPasswd)){
			return true;
		}
		return false;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "MemberCommand [id=" + id + ", passwd=" + passwd + ", name=" + name + ", phone=" + phone + ", email="
				+ email + ", zipcode=" + zipcode + ", address1=" + address1 + ", address2=" + address2 + ", lev=" + lev
				+ ", reg_date=" + reg_date + "]";
	}
	
	
}
