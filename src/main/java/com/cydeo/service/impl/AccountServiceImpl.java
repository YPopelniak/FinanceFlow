package com.cydeo.service.impl;
import com.cydeo.entity.Account;
import com.cydeo.enums.AccountType;
import com.cydeo.repository.AccountRepository;
import com.cydeo.service.AccountService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class AccountServiceImpl implements AccountService {

    AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account createNewAccount(BigDecimal balance, Date createData, AccountType accountType, Long userId) {

        Account account = Account.builder().id(UUID.randomUUID()).userId(userId)
                .balance(balance).accountType(accountType).creationDate(createData).build();

                return accountRepository.save(account);
    }

    @Override
    public List<Account> listAllAccount(){
        return accountRepository.findAll();
    }

}
