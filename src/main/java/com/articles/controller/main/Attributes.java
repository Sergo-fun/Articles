package com.articles.controller.main;

import com.articles.model.Ads;
import com.articles.model.enums.*;
import org.springframework.ui.Model;

public class Attributes extends Main {

    protected void AddAttributes(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
    }

    protected void AddAttributesEnums(Model model) {
        model.addAttribute("categories", Category.values());
        model.addAttribute("types", Type.values());
        model.addAttribute("regions", Region.values());
        model.addAttribute("pricelist", pricelistRepo.findAll());
    }

    protected void AddAttributesUsers(Model model) {
        AddAttributes(model);
        model.addAttribute("users", usersRepo.findAll());
        model.addAttribute("roles", Role.values());
    }

    protected void AddAttributesAd(Model model, Long id) {
        AddAttributes(model);
        Ads ad = adsRepo.getReferenceById(id);
        ad.setCounter(ad.getCounter() + 1);
        adsRepo.save(ad);
        model.addAttribute("ad", ad);
    }

    protected void AddAttributesAdAdd(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
    }

    protected void AddAttributesAdEdit(Model model, Long id) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("ad", adsRepo.getReferenceById(id));
    }

    protected void AddAttributesIndex(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("ads", adsRepo.findAllByStatus(adStatus.ACTIVE));
    }

    protected void AddAttributesAdMy(Model model) {
        AddAttributes(model);
        model.addAttribute("ads", getUser().getAds());
    }

    protected void AddAttributesAdApps(Model model) {
        AddAttributes(model);
        model.addAttribute("ads", adsRepo.findAllByStatus(adStatus.WAITING));
    }

    protected void AddAttributesIndexSearch(Model model, String search, Category category, Type type, Region region) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("bSelect", category);
        model.addAttribute("fSelect", type);
        model.addAttribute("tSelect", region);
        model.addAttribute("search", search);
        model.addAttribute("ads", adsRepo.findAllByNameContainingAndStatusAndCategoryAndRegionAndType(search, adStatus.ACTIVE, category, region, type));
    }

    protected void AddAttributesIndexSearchCategory(Model model, Category category) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("bSelect", category);
        model.addAttribute("ads", adsRepo.findAllByStatusAndCategory(adStatus.ACTIVE, category));
    }

    protected void AddAttributesIndexSearchType(Model model, Type type) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("fSelect", type);
        model.addAttribute("ads", adsRepo.findAllByStatusAndType(adStatus.ACTIVE, type));
    }

    protected void AddAttributesIndexSearchRegion(Model model, Region region) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("tSelect", region);
        model.addAttribute("ads", adsRepo.findAllByStatusAndRegion(adStatus.ACTIVE, region));
    }

    protected void AddAttributesStats(Model model) {

    }
}
