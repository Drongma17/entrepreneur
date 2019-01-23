package com.thonglam.spsecurity.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @RequestMapping(value = "svc/v1/public/accounts/{accountNumber}")
    public String getPublicAccountDataLinkedTo(@PathVariable final int accountNumber){

        return "public account linked to " + accountNumber;
}


    @RequestMapping(value = "svc/v1/private/accounts/{accountNumber}")
    public String getPrivateAccountDataLinkedTo(@PathVariable final int accountNumber){

        return "private account linked to " + accountNumber;
    }


    @RequestMapping(value = "svc/v1/private/ac/admin/counts/{accountNumber}")
    public String getExtraPrivateAccountDataLinkedTo(@PathVariable final int accountNumber){

        return "private account linked to " + accountNumber;
    }
}
