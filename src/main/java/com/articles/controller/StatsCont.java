package com.articles.controller;

import com.articles.controller.main.Attributes;
import com.articles.model.Ads;
import com.articles.model.enums.adStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/stats")
public class StatsCont extends Attributes {
    @GetMapping
    public String Statistics(Model model) {
        AddAttributes(model);
        model.addAttribute("waiting", adsRepo.findAllByStatus(adStatus.WAITING).size());
        model.addAttribute("active", adsRepo.findAllByStatus(adStatus.ACTIVE).size());
        model.addAttribute("sold", adsRepo.findAllByStatus(adStatus.SOLD).size());

        List<Ads> ads = adsRepo.findAll();

        ads.sort(Comparator.comparing(Ads::getCounter));
        Collections.reverse(ads);

        String[] counterString = new String[5];
        int[] counterInt = new int[5];

        for (int i = 0; i < ads.size(); i++) {
            if (i == 5) break;
            counterString[i] = ads.get(i).getName();
            counterInt[i] = ads.get(i).getCounter();
        }

        model.addAttribute("counterString", counterString);
        model.addAttribute("counterInt", counterInt);

        return "stats";
    }
}
