package org.example.testproject.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
public class BoardDTO {

    private Long boardNum;
    private String title;
    private String content;
    private Long memberNum;	// 맴버테이블과 fk
    private String url;
    private Long boardcase;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private Long price;
}
