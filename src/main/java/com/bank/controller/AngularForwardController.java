
package com.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AngularForwardController {

   
    
    @GetMapping("/bank")
    public String forwardBank() {
        return "forward:/bank/index.html";
    }

    @GetMapping("/bank/{route:[a-zA-Z0-9-]+}")
    public String forwardAngularRoutes() {
        return "forward:/bank/index.html";
    }
}



