package com.cydeo.controller;

import com.cydeo.repository.AccountRepository;
import com.cydeo.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/list")
    public String getIndexPage(Model model){
        model.addAttribute("accountList", accountService.listAllAccount());

        return "account-list";
    }


}
