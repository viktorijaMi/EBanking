package com.ebanking.ebanking.service.impl;


import com.ebanking.ebanking.model.PrimaryAccount;
import com.ebanking.ebanking.model.PrimaryTransaction;
import com.ebanking.ebanking.model.exceptions.PrimaryAccountNotFoundException;
import com.ebanking.ebanking.repository.PrimaryAccountRepository;
import com.ebanking.ebanking.repository.PrimaryTransactionRepository;
import com.ebanking.ebanking.service.PrimaryAccountService;
import com.ebanking.ebanking.service.PrimaryTransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrimaryTransactionImpl implements PrimaryTransactionService {

    private final PrimaryAccountRepository primaryAccountRepository;
    private final PrimaryTransactionRepository primaryTransactionRepository;

    public PrimaryTransactionImpl(PrimaryAccountRepository primaryAccountRepository, PrimaryTransactionRepository primaryTransactionRepository) {
        this.primaryAccountRepository = primaryAccountRepository;
        this.primaryTransactionRepository = primaryTransactionRepository;
    }

    @Override
    public PrimaryTransaction createTransaction(String description, Double amount, Long fromAccountId, Long toAccountId) {
        PrimaryAccount fromAccount = this.primaryAccountRepository.findById(fromAccountId)
                .orElseThrow(() -> new PrimaryAccountNotFoundException(fromAccountId));
        PrimaryAccount toAccount = this.primaryAccountRepository.findById(toAccountId)
                .orElseThrow(() -> new PrimaryAccountNotFoundException(toAccountId));

        PrimaryTransaction primaryTransaction = new PrimaryTransaction(description, amount, fromAccount, toAccount);

        this.primaryTransactionRepository.save(primaryTransaction);

        fromAccount.getPrimaryTransactionList().add(primaryTransaction);
        this.primaryAccountRepository.save(fromAccount);
        return primaryTransaction;
    }

    @Override
    public List<PrimaryTransaction> getTransactions(Long id) {
        PrimaryAccount primaryAccount = this.primaryAccountRepository.findById(id)
                .orElseThrow(() -> new PrimaryAccountNotFoundException(id));
        return primaryAccount.getPrimaryTransactionList();
    }
}
