package org.example.testproject.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.example.testproject.Service.MemberService;
import org.example.testproject.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    /**회원가입 총 인원 */
    @Override
    public int getMemberTotalCount() {
        return memberMapper.selectMemberTotalCount();
    }
}
