package com.kids.api.kidsAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    KidsService kService;
    
    

}
