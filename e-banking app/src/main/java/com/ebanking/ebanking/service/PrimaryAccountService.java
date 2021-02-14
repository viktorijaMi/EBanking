package com.ebanking.ebanking.service;

import com.ebanking.ebanking.model.PrimaryAccount;
import com.ebanking.ebanking.model.User;
import javafx.beans.binding.DoubleExpression;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PrimaryAccountService {
    PrimaryAccount createAccount(String username, int number, Double ballance);
    Optional<PrimaryAccount> getAccount(Long id);
    User addMoneyToAccount(Long id, Double ballance);
    List<PrimaryAccount> findAll();
    PrimaryAccount transferMoney(Long fromAccountId, Long toAccountId, Double transferAmount);
}
