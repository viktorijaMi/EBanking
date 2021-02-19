package com.ebanking.ebanking.web;

import com.ebanking.ebanking.model.PrimaryTransaction;
import com.ebanking.ebanking.service.PrimaryAccountService;
import com.ebanking.ebanking.service.PrimaryTransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
    private final PrimaryTransactionService primaryTransactionService;
    private final PrimaryAccountService primaryAccountService;

    public TransactionController(PrimaryTransactionService primaryTransactionService, PrimaryAccountService primaryAccountService) {
        this.primaryTransactionService = primaryTransactionService;
        this.primaryAccountService = primaryAccountService;
    }

    @GetMapping("/{id}")
    public String getTransactionsPage(@PathVariable Long id,
                                      Model model) {
        List<PrimaryTransaction> transactionsList = this.primaryTransactionService.getTransactions(id);
        model.addAttribute("primaryAccountNumber", this.primaryAccountService.getAccount(id).get().getNumber());
        model.addAttribute("transactionsList", transactionsList);
        model.addAttribute("bodyContent", "transactions");
        return "master-template";
    }
}
