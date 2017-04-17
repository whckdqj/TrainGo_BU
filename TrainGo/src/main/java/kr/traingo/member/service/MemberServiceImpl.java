package kr.traingo.member.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.traingo.member.dao.MemberMapper;
import kr.traingo.member.domain.MemberCommand;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

	@Resource
	private MemberMapper memberMapper;
	
	
	@Override
	public List<MemberCommand> getMemberList(Map<String, Object> map) {
		return memberMapper.getMemberList(map);
	}

	@Override
	public int getMemberCount() {
		return memberMapper.getMemberCount();
	}
	
	@Override
	public MemberCommand getMember(String id) {
		return memberMapper.getMember(id);
	}
		
	@Override
	public void updateMember(MemberCommand member) {
		memberMapper.updateMember(member);
		
	}
	
	@Override
	public void deleteMember(String id) {
		memberMapper.deleteMember(id);
	}
	
	@Override
	public void insert(MemberCommand member) {
		memberMapper.insert(member);
	}

	@Override
	public MemberCommand selectMember(String id) {
		return memberMapper.selectMember(id);
	}
	
	@Override
	public void update(MemberCommand member) {
		memberMapper.update(member);
	}

	@Override
	public void delete(String id) {
		
		//해당 id 삭제
		memberMapper.delete(id);
	}

	
}















