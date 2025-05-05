package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AccountService {
    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // 2: Our API should be able to process User logins.
    // Also used for registerAccount method (Requirement 1)
    public Account getAccount(Account account) {
        Account loginAccount = accountRepository.getAccountByUsername(account.getUsername());

        if (loginAccount != null && loginAccount.getUsername().equals(account.getUsername()) && loginAccount.getPassword().equals(account.getPassword()))
            return loginAccount;
        else
            return null;
    }

    // Checks if an account with this username already exists
    // Used to check for duplicate usernames (Requirement 1)
    public boolean checkIfUsernameExists(Account account) {
        Account loginAccount = accountRepository.getAccountByUsername(account.getUsername());

        if (loginAccount != null)
            return true;
        else 
            return false;
    }

    // 1: Our API should be able to process new User registrations. 
    public Account registerAccount(Account account) {
        if (account.getUsername().length() > 0 && account.getPassword().length() >= 4 && this.getAccount(account) == null) {
            accountRepository.save(account);
            return account;
        }   else {
            return null;
        }
    } 
}
