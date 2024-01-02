package org.example.testproject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.testproject.entity.Member;

@Mapper
public interface MemberMapper {
    // test 용
    void insertMember(Member member);

    /**회원가입 총 인원 */
    int selectMemberTotalCount();

}
