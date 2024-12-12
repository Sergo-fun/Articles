package com.articles.controller;

import com.articles.controller.main.Attributes;
import com.articles.model.enums.Category;
import com.articles.model.enums.Region;
import com.articles.model.enums.Type;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexCont extends Attributes {

    @GetMapping
    public String index1(Model model) {
        AddAttributesIndex(model);
        return "index";
    }

    @GetMapping("/index")
    public String index2(Model model) {
        AddAttributesIndex(model);
        return "index";
    }

    @PostMapping("/search")
    public String search(Model model, @RequestParam String search, @RequestParam Category category, @RequestParam Type type, @RequestParam Region region) {
        AddAttributesIndexSearch(model, search, category, type, region);
        return "index";
    }

    @GetMapping("/search/category/{category}")
    public String search(Model model, @PathVariable Category category) {
        AddAttributesIndexSearchCategory(model, category);
        return "index";
    }

    @GetMapping("/search/type/{type}")
    public String search(Model model, @PathVariable Type type) {
        AddAttributesIndexSearchType(model, type);
        return "index";
    }

    @GetMapping("/search/region/{region}")
    public String search(Model model, @PathVariable Region region) {
        AddAttributesIndexSearchRegion(model, region);
        return "index";
    }
}
