package org.example.testproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Table(indexes = {@Index(name = "member_index",columnList = "id")})
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /** member 테이블 고유 pk */
    private Long memberNum;

    @Column(length =100, nullable = false, unique = true)
    /** member ID */
    private String id;

    @Column(length = 100, nullable = false)
    /** member 비밀번호 */
    private String pw;

    @Column(length = 100, nullable = false)
    /** member 이름 */
    private String name;

    @ColumnDefault("0")
    /** member 현재 가지고있는 포인트 */
    private Long point;

    /** 권한*/
    private Boolean purview;

    @Column(length = 300)
    /** member 이메일 */
    private String email;

    @ColumnDefault("0")
    /** member 경험치(높을수록 티어가오름) */
    private Long exvalue;

    /** member와 memberRole연결 */
    @ElementCollection(fetch= FetchType.EAGER)
    @Builder.Default
    private Set<MemberRole> roleset = new HashSet<>();
}
