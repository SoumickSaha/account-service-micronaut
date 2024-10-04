package com.example.service;

import java.math.BigDecimal;
import java.util.Optional;

import com.example.model.Account;
import com.example.repo.AccountRepository;

import jakarta.inject.Singleton;

@Singleton
public class AccountService {

    private final AccountRepository accountRepository;
    
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    public BigDecimal getAccountBalance(Long accountId) {
        return accountRepository.findById(accountId)
                .map(Account::getBalance)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account updateAccountBalance(Long accountId, BigDecimal newBalance) {
        return accountRepository.findById(accountId)
                .map(account -> {
                    account.setBalance(newBalance);
                    return accountRepository.update(account);
                })
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public boolean validateAccount(Long accountId) {
        return accountRepository.existsById(accountId);
    }
}

