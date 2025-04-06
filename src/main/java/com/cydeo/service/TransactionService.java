package com.cydeo.service;

import com.cydeo.entity.Account;
import com.cydeo.entity.Transaction;
import com.cydeo.exception.AccountOwnerException;
import com.cydeo.exception.BadRequestException;
import com.cydeo.exception.BalanceNotSufficientException;
import com.cydeo.exception.RecordNotFoundException;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface TransactionService {

    Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) throws RecordNotFoundException, BadRequestException, AccountOwnerException, BalanceNotSufficientException;

    List<Transaction> findAllTransaction();
}
