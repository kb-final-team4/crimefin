package com.service.crimefin.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SendNumResponseDTO {
    String resultNum;
    Timestamp startTime;
}
