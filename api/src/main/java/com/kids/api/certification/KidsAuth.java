package com.kids.api.certification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KidsAuth {
    private int kidId;
    private String password;
}
