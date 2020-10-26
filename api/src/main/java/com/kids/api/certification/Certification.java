package com.kids.api.certification;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Certification {
	private String certificationNo;
	private int parentId;
	private int kidId;
	private LocalDateTime expiredTime;
	
}
