package com.cydeo.service.impl;

import com.cydeo.entity.Account;
import com.cydeo.entity.Transaction;
import com.cydeo.enums.AccountType;
import com.cydeo.exception.AccountOwnerException;
import com.cydeo.exception.BadRequestException;
import com.cydeo.exception.BalanceNotSufficientException;
import com.cydeo.exception.RecordNotFoundException;
import com.cydeo.repository.AccountRepository;
import com.cydeo.repository.TransactionRepository;
import com.cydeo.service.TransactionService;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }


    @Override
    public Transaction makeTransfer(Account sender, Account receiver, BigDecimal amount, Date creationDate, String message) throws RecordNotFoundException, BadRequestException, AccountOwnerException, BalanceNotSufficientException {

        validateAccount(sender, receiver);
        checkAccountOwnership(sender, receiver);
        executeBalanceAndUpdateIfRequire(amount,sender,receiver);

        Transaction transaction = Transaction.builder().amount(amount).sender(sender.getId()).receiver(receiver.getId())
                .createDate(creationDate).message(message).build();

        return transactionRepository.save(transaction);
    }

    private void executeBalanceAndUpdateIfRequire(BigDecimal amount, Account sender, Account receiver) throws BalanceNotSufficientException {
        if (checkSenderBalance(sender,amount)){
            sender.setBalance(sender.getBalance().subtract(amount));
            receiver.setBalance(receiver.getBalance().add(amount));
        }else {
            throw new BalanceNotSufficientException("Balance is not enough for this transfer");
        }
    }

    private boolean checkSenderBalance(Account sender, BigDecimal amount) {
        return sender.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >=0;
    }

    private void checkAccountOwnership(Account sender, Account receiver) throws AccountOwnerException {
        if (sender.getAccountType().equals(AccountType.SAVING) || receiver.getAccountType().equals(AccountType.SAVING) && !sender.getId().equals(receiver.getId())){
            throw new AccountOwnerException("Sender or Receiver can't be null");
        }
    }

    public void validateAccount(Account sender, Account receiver) throws BadRequestException, RecordNotFoundException {

        if (Stream.of(sender, receiver).anyMatch(Objects::isNull)) {
            throw new BadRequestException("Sender or Receiver can't be null");
        }
        if (sender.getId().equals(receiver.getId())){
            throw new BadRequestException("Sender account need to be different then receiver account");
        }

        findAccountById(sender.getId());
        findAccountById(receiver.getId());

    }

    private void findAccountById(UUID id) throws RecordNotFoundException {
        accountRepository.findById(id);
    }

    @Override
    public List<Transaction> findAllTransaction() {
        return transactionRepository.findAll();
    }
}
