package com.ebanking.ebanking.web;

import com.ebanking.ebanking.model.PrimaryAccount;
import com.ebanking.ebanking.model.User;
import com.ebanking.ebanking.service.PrimaryAccountService;
import com.ebanking.ebanking.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping({"/home", ""})
public class HomeController {

    private final PrimaryAccountService primaryAccountService;
    private final UserService userService;

    public HomeController(PrimaryAccountService primaryAccountService, UserService userService) {
        this.primaryAccountService = primaryAccountService;
        this.userService = userService;
    }

    @GetMapping
    public String getHomePage(@RequestParam(required = false) String message,
                              Model model,
                              HttpServletRequest request,
                              Principal principal) {
        if (message != null && !message.isEmpty()) {
            model.addAttribute("hasMessage", true);
            model.addAttribute("message", "TRANSFER SUCCESSFUL");
        }
        User user = userService.findByUsername(principal.getName()).get();
        model.addAttribute("user", user);
        model.addAttribute("bodyContent", "home");
        return "master-template";
    }

    @GetMapping("/add")
    public String getAddMoneyPage(Model model,
                                  HttpServletRequest request) {
        List<PrimaryAccount> primaryAccountList = this.primaryAccountService.findAll();
        primaryAccountList.remove(userService.findByUsername(request.getRemoteUser()).get().getPrimaryAccount());
        model.addAttribute("primaryAccountList", primaryAccountList);
        model.addAttribute("bodyContent", "addmoney");
        return "master-template";
    }

    @PostMapping("/add")
    public String addMoneyToAccount(@RequestParam Long id,
                                    @RequestParam Double ballance,
                                    HttpServletRequest request) {
        User user = this.primaryAccountService.addMoneyToAccount(id, ballance);
        request.getSession().setAttribute("user", user);
        return "redirect:/home";
    }
}
