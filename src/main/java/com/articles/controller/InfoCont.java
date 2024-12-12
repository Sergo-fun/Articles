package com.articles.controller;

import com.articles.controller.main.Attributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class InfoCont extends Attributes {

    @GetMapping
    public String index1(Model model) {
        AddAttributes(model);
        return "info";
    }
}
