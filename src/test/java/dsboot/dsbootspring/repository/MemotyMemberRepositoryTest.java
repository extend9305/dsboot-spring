package dsboot.dsbootspring.repository;

import dsboot.dsbootspring.domain.Member;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;



public class MemotyMemberRepositoryTest {
    MemoryMemberRepository memberRepository =new MemoryMemberRepository();

    @AfterEach
    public void aferEach(){
        memberRepository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get();

        //Assertions.assertEquals(member,result);
        assertThat(member).isEqualTo(result);


    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);


        Member result1= memberRepository.findByName("spring1").get();
        assertThat(result1).isEqualTo(member1);
    }

    @Test
    public  void  findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();

        assertThat(result.size()).isEqualTo(3);
    }




}
