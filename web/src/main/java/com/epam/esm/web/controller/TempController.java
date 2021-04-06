package com.epam.esm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {
    @GetMapping(value = "/person")
    public String welcomePage() {
      int i=0;
        return "redirect:/";
    }
}
