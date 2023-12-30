package org.example.testproject.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.testproject.entity.Member;
import org.example.testproject.repository.MemberRepository;
import org.example.testproject.security.dto.AuthMemberDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("티테일 서비스에서 유저 이름이 잘 찍히는지 : "+username);

        Optional<Member> result = memberRepository.findByEmail(username, false);
        if(result.isEmpty()) {
            throw new UsernameNotFoundException("이메일이나 소셜 가입 여부를 확인해주세요.");
        }
        Member member = result.get();

        AuthMemberDTO memberDTO = new AuthMemberDTO(member.getId()
                , member.getPw()
                , member.getPurview()
                , member.getRoleset().stream()
                .map(i-> new SimpleGrantedAuthority("ROLE_"+ i.name()))
                .collect(Collectors.toSet()));
        memberDTO.setName(member.getName());
        memberDTO.setPurview(member.getPurview());

        return memberDTO;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
