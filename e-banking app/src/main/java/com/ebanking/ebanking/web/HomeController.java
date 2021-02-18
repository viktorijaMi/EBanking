package com.ebanking.ebanking.web;

import com.ebanking.ebanking.model.PrimaryAccount;
import com.ebanking.ebanking.model.User;
import com.ebanking.ebanking.service.PrimaryAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping({"/home", ""})
public class HomeController {

    private final PrimaryAccountService primaryAccountService;

    public HomeController(PrimaryAccountService primaryAccountService) {
        this.primaryAccountService = primaryAccountService;
    }

    @GetMapping
    public String getHomePage(@RequestParam(required = false) String message,
                              Model model,
                              HttpServletRequest request){
        if (message != null && !message.isEmpty()){
            model.addAttribute("hasMessage", true);
            model.addAttribute("message", "TRANSFER SUCCESSFUL");
        }
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("bodyContent", "home");
        return "master-template";
    }

    @GetMapping("/add/{id}")
    public String getAddMoneyPage(@PathVariable Long id, Model model){
        PrimaryAccount primaryAccount = this.primaryAccountService.getAccount(id).get();
        model.addAttribute("primaryAccount", primaryAccount);
        return "addmoney";
    }

    @PostMapping("/add/{id}")
    public String addMoneyToAccount(@PathVariable Long id,
                                    @RequestParam Double ballance,
                                    HttpServletRequest request){
        User user = this.primaryAccountService.addMoneyToAccount(id, ballance);
        request.getSession().setAttribute("user", user);
        return "redirect:/home";
    }
}
