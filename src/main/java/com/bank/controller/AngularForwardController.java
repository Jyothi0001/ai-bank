
package com.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AngularForwardController {

    @GetMapping({
        "/bank/login",
        "/bank/register",
        "/bank/dashboard",
        "/bank/account",
        "/bank/transfer",
        "/bank/transactions",
        "/bank/otp",
        "/bank/face-auth",
        "/bank/loan",
        "/bank/chatbot",
        "/bank/profile",
        "/bank/admin"
    })
    public String forwardToAngular() {
        return "forward:/bank/index.html";
    }
}

