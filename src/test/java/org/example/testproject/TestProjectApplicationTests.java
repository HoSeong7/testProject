package org.example.testproject;

import org.example.testproject.entity.Member;
import org.example.testproject.mapper.MemberMapper;
import org.example.testproject.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestProjectApplicationTests {

    @Autowired
    private MemberRepository mr;

    @Autowired
    private MemberMapper mapper;

    @Test
    void contextLoads() {
    }

//    @Test
//    public void jpaAndMybatis(){
//        Member m = new Member();
//        m.setMemberNum("1");
//        m.setId("asdfss");
//        m.setPassword("3");
//        mr.save(m);
//
//        Member m2 = new Member();
//        m2.setId("qwerss");
//        m2.setMemberNum("2");
//        m2.setPassword("456");
//        mapper.insertMember(m2);
//    }
    //npm install -g @vue/cli

}
