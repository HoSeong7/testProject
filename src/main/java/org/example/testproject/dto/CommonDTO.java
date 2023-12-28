package org.example.testproject.dto;

import lombok.*;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
public class CommonDTO {
    private int memberCount;
    private String url;
    private Timestamp regDate;
    private Timestamp updateDate;
}
