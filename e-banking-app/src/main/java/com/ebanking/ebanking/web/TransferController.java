package com.ebanking.ebanking.web;

import com.ebanking.ebanking.model.PrimaryAccount;
import com.ebanking.ebanking.model.User;
import com.ebanking.ebanking.model.exceptions.NotSupportedTransferException;
import com.ebanking.ebanking.service.PrimaryAccountService;
import com.ebanking.ebanking.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/transfer")
public class TransferController {

    private final PrimaryAccountService primaryAccountService;
    private final UserService userService;

    public TransferController(PrimaryAccountService primaryAccountService, UserService userService) {
        this.primaryAccountService = primaryAccountService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getTransferPage(@RequestParam(required = false) String error,
                                  @PathVariable Long id,
                                  Model model,
                                  HttpServletRequest request) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        PrimaryAccount primaryAccount = this.primaryAccountService.getAccount(id).get();
        List<PrimaryAccount> primaryAccountList = this.primaryAccountService.findAll();
        primaryAccountList.remove(primaryAccount);
        request.getSession().setAttribute("account", primaryAccount);
        model.addAttribute("primaryAccount", primaryAccount);
        model.addAttribute("primaryAccountList", primaryAccountList);
        model.addAttribute("bodyContent", "transfer");
        return "master-template";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping
    public String transferMoney(@RequestParam Long toAccountId,
                                @RequestParam String description,
                                @RequestParam Double transferAmount,
                                HttpServletRequest request) {
        PrimaryAccount fromAccount = (PrimaryAccount) request.getSession().getAttribute("account");
        try {
            this.primaryAccountService.transferMoney(fromAccount.getId(), toAccountId, description, transferAmount);
        } catch (NotSupportedTransferException ex) {
            return "redirect:/transfer/" + fromAccount.getId() + "?error=" + ex.getMessage();
        }
        User user = this.userService.findByUsername(fromAccount.getUsername()).get();
        request.getSession().setAttribute("user", user);
        return "redirect:/home?message=true";
    }


}
