package com.kids.api.account;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class User {
    private Integer id;
    
    @NonNull
    private Long uuid;
    
    @NonNull
    private String provider;
    
    private String name;
    
    private String email;
    
    private Boolean enabled;
    
    private Timestamp disableTime;
}
