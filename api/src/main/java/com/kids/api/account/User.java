package com.kids.api.account;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class User {
    private Integer id;
    
    private Long uuid;
    
    private String provider;
    
    private String name;
    
    private String email;
    
    private Boolean enabled;
    
    private Timestamp disableTime;
}
