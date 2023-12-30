package org.example.testproject.security.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Map;

@Getter
@ToString
@Setter
@Slf4j
public class AuthMemberDTO extends User {
    private String id;
    private String pw;
    private String name;
    private boolean purview;

    private Map<String, Object> attr;

    public AuthMemberDTO(String id,
                         String pw,
                         boolean purview,
                         Collection<? extends GrantedAuthority> authorities) {
        super(id, pw, authorities);
        this.id = id;
        this.pw = pw;
        this.purview = purview;
        log.info("================ authMemberDTO임 ================"+id);
    }

}
