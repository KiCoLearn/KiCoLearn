package com.kids.api.account.parents;

import com.kids.api.account.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString(callSuper=true)
@SuperBuilder
public class Parents extends User {
    String token;
}
