package org.example.testproject.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
@EnableWebSecurity
public class SecurityConfig{


    private static final String[] WHITE_LIST = {
            "/",
            "/index",
            "/login",
            "/css/**",
            "/js/**",
            "/img/**",
            "/vendor/**"
    };
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http.csrf(AbstractHttpConfigurer::disable);
//        http.authorizeHttpRequests(
//                authorize -> authorize
//                        .requestMatchers(WHITE_LIST).permitAll()
//                        .anyRequest().authenticated()
//        );
//
//        return http.build();
//
//    }

//    @Bean
//    public WebSecurityCustomizer cofigure(){
//        return (web)-> web.ignoring()
//                .requestMatchers(WHITE_LIST);
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        return http
//                .authorizeHttpRequests()
//                .requestMatchers("/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/index")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login")
//                .invalidateHttpSession(true)
//                .and()
//                .csrf().disable()
//                .build();
//
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();

        http.authorizeHttpRequests(authorize-> {
            try {
                authorize
                        .requestMatchers(WHITE_LIST).permitAll() // 전체 권한 허용
                        .requestMatchers("/adminList").hasRole("ADMIN") // ADMIN만 접근가능
                        .and()
                        .formLogin()
                        .loginPage("/login")
//                        .loginProcessingUrl("/")  // 로그인 처리 url설정인데 필요하면 추가로 만들어서 넣으면 될듯
                        .defaultSuccessUrl("/index")
//                        .successHandler(new 핸들러위치) // 로그인 성공 후 처리할 핸들러
//                        .failureHandler(new 핸들러위치) // 로그인 실패 후 처리할 핸들러
                        .permitAll()
                        .and()
                        .logout()
//                        .logoutUrl("/") // 로그아웃 처리 url
//                        .logoutSuccessUrl("") // 로그아웃 성공 후 이동할 페이지인데 defaultSuccessURL이 있어서 상관없는지 확인하기
                        .deleteCookies("JSESSIONID");  // JSESSIONID란 톰캣 컨테이너에서 세션을 유지하기 위해 발급하는키이며 쿠키제거용도
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        });

        return http.build();
    }

}
