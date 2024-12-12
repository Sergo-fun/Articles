package com.articles.controller;

import com.articles.controller.main.Attributes;
import com.articles.model.Pricelist;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pricelist")
public class PricelistCont extends Attributes {
    @GetMapping
    public String pricelist(Model model) {
        AddAttributes(model);
        model.addAttribute("pricelist", pricelistRepo.findAll());
        return "pricelist";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name, @RequestParam int days, @RequestParam float price) {
        pricelistRepo.save(new Pricelist(name, days, price));
        return "redirect:/pricelist";
    }

    @PostMapping("/{id}/edit")
    public String edit(@RequestParam String name, @RequestParam int days, @RequestParam float price, @PathVariable Long id) {
        Pricelist pricelist = pricelistRepo.getReferenceById(id);
        pricelist.set(name, days, price);
        pricelistRepo.save(pricelist);
        return "redirect:/pricelist";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        pricelistRepo.deleteById(id);
        return "redirect:/pricelist";
    }
}
