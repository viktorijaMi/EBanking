package com.ebanking.ebanking.service;

import com.ebanking.ebanking.model.PrimaryTransaction;

import java.time.LocalDateTime;
import java.util.List;

public interface PrimaryTransactionService {
    PrimaryTransaction createTransaction(String description, Double amount, Long fromAccountId, Long toAccountId);
    List<PrimaryTransaction> getTransactions(Long id);
}
