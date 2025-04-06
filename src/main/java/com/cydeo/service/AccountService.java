package com.cydeo.service;

import com.cydeo.entity.Account;
import com.cydeo.enums.AccountType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AccountService {

    Account createNewAccount(BigDecimal bigDecimal, Date createData, AccountType accountType, Long userId);

    List<Account> listAllAccount();
}
