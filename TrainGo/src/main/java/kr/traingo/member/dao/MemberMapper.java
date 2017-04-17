package kr.traingo.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.traingo.member.domain.MemberCommand;


public interface MemberMapper {
	//회원목록 관리용
	public List<MemberCommand> getMemberList(Map<String,Object> map);
	@Select("SELECT count(*) FROM fmember")
	public int getMemberCount();
	
	@Select("SELECT * FROM fmember WHERE id = #{id}")
	public MemberCommand getMember(String id);
	@Update("UPDATE fmember SET name=#{name},passwd=#{passwd},phone=#{phone},email=#{email},zipcode=#{zipcode},address1=#{address1},address2=#{address2} WHERE id=#{id}")
	public void updateMember(MemberCommand member);
	@Delete("DELETE FROM fmember WHERE id=#{id}")
	public void deleteMember(String id);
	
	//로그인 회원관리용
	@Insert("INSERT INTO fmember (id,passwd,name,phone,email,zipcode,address1,address2,lev,reg_date) VALUES (#{id},#{passwd},#{name},#{phone},#{email},#{zipcode},#{address1},#{address2},#{lev},sysdate)")
	public void insert(MemberCommand member);
	@Select("SELECT * FROM fmember WHERE id = #{id}")
	public MemberCommand selectMember(String id);
	@Update("UPDATE fmember SET name=#{name},passwd=#{passwd},phone=#{phone},email=#{email},zipcode=#{zipcode},address1=#{address1},address2=#{address2} WHERE id=#{id}")
	public void update(MemberCommand member);
	@Delete("DELETE FROM fmember WHERE id=#{id}")
	public void delete(String id);
}






