package hello.hellospring;

import hello.hellospring.Repository.*;
import hello.hellospring.Service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {


    // 바로 autowaired하거나 생성자를 통해서 활용
    // @Autowired
    private DataSource dataSource;

//    @Autowired
//    public SpringConfig(DataSource dataSource){ this.dataSource = dataSource;}

    /*
    * 원래 스펙에서는 이렇게 받아와야함
    * @PersistenceContext
    * private EntityManager em;
    * */

    private EntityManager em;
    @Autowired
    public SpringConfig(EntityManager em){this.em = em;}

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){

//        return new MemoryMemberRepository();      // MemMoryRepository
//        return new JdbcMemberRepository(dataSource);  // JdbcRepository
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }

}
