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
	 * 회원 가입
	 * */
	public Long join(Member member) {
		
		//중복회원 검증
		validateDuplicateMember(member); 
		
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validateDuplicateMember(Member member) {
		//같은 중복임름이 있으면  X
		memberRepository.findByName(member.getName())
				.ifPresent(m->{
					throw new IllegalStateException("이미 존재하는 이름입니다.");
				});

	}

	/**
	 * 전체회원 조회 
	**/
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
	
}
