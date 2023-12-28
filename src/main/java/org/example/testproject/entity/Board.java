package org.example.testproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Builder
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /** Board 고유 pk */
    private Long boardNum;

    @Column(length = 100, nullable = false)
    /** Board 제목 */
    private String title;

    @Column(length = 3000, nullable = false)
    /** Board 내용 */
    private String content;

    /** member테이블과 fk */
    private Long memberNum;	// 맴버테이블과 fk

    @Column(length = 100)
    /** Board 사진 */
    private String url;

    @ColumnDefault("0")
    /** Board 가격 */
    private Long price;

    /** 0: 샤브샤브 메뉴  1: 추가메뉴   2: 음료/주류  3: 이벤트 */
    private Long boardcase;
}
