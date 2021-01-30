package dsboot.dsbootspring.service;

import java.util.List;
import java.util.Optional;

import dsboot.dsbootspring.domain.Member;
import dsboot.dsbootspring.repository.MemberRepository;
import dsboot.dsbootspring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	private final MemberRepository memberRepository;
	@Autowired
	public MemberService(MemoryMemberRepository memberRepository) {
		 this.memberRepository = memberRepository;
	}

	/*
	 * ȸ�� ����
	 * */
	public Long join(Member member) {
		
		//�ߺ�ȸ�� ����
		validateDuplicateMember(member); 
		
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validateDuplicateMember(Member member) {
		//���� �ߺ��Ӹ��� ������  X
		memberRepository.findByName(member.getName())
				.ifPresent(m->{
					throw new IllegalStateException("�̹� �����ϴ� �̸��Դϴ�.");
				});

	}

	/**
	 * ��üȸ�� ��ȸ 
	**/
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
	
}
