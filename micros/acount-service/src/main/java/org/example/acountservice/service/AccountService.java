package org.example.acountservice.service;

import org.example.acountservice.entity.Account;
import org.example.acountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public boolean debitAccountByClientId(Long clientId, double amount) {
        Account account = accountRepository.findByClientId(clientId).orElse(null);
        if (account != null && account.getBalance() >= amount) {
            account.debit(amount);
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    public Account getAccountByClientId(Long clientId) {
        return accountRepository.findByClientId(clientId).orElse(null);
    }


    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }


    public Account getAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElse(null);
    }


}
