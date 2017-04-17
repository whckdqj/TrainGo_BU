package kr.traingo.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import kr.traingo.member.domain.MemberCommand;

@Transactional
public interface MemberService {
	//ȸ����� ������
	public List<MemberCommand> getMemberList(Map<String,Object> map);
	public int getMemberCount();
	public MemberCommand getMember(String id);
	public void updateMember(MemberCommand member);
	public void deleteMember(String id);
	
	//�α��� ȸ��������
	public void insert(MemberCommand member);
	@Transactional(readOnly=true)
	public MemberCommand selectMember(String id);
	public void update(MemberCommand member);
	public void delete(String id);
	
}
