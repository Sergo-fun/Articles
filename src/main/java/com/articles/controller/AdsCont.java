package com.articles.controller;

import com.articles.controller.main.Attributes;
import com.articles.model.Ads;
import com.articles.model.Comments;
import com.articles.model.Requests;
import com.articles.model.enums.Category;
import com.articles.model.enums.Region;
import com.articles.model.enums.Type;
import com.articles.model.enums.adStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/ads")
public class AdsCont extends Attributes {

    @GetMapping("/{id}")
    public String Ad(Model model, @PathVariable Long id) {
        AddAttributesAd(model, id);
        return "ad";
    }

    @PostMapping("/{id}/comment/add")
    public String CommentAdd(@PathVariable Long id, @RequestParam String text) {
        Ads ad = adsRepo.getReferenceById(id);
        ad.addComment(new Comments(getUser().getUsername(), DateNow(), text));
        adsRepo.save(ad);
        return "redirect:/ads/{id}";
    }

    @GetMapping("/add")
    public String AdAdd(Model model) {
        AddAttributesAdAdd(model);
        model.addAttribute("categories", Category.values());
        return "ad_add";
    }

    @GetMapping("/pay/{adId}")
    public String AdPay(Model model, @PathVariable Long adId) {
        Ads ad = adsRepo.getReferenceById(adId);
        ad.setStatus(adStatus.PAY);
        adsRepo.save(ad);
        AddAttributesAdAdd(model);
        return "redirect:/ads/{adId}";
    }

    @GetMapping("/conf/{adId}")
    public String AdConf(Model model, @PathVariable Long adId) {
        Ads ad = adsRepo.getReferenceById(adId);
        ad.setStatus(adStatus.ACTIVE);
        adsRepo.save(ad);
        AddAttributesAdAdd(model);
        return "redirect:/ads/{adId}";
    }

    @GetMapping("/refused/{adId}")
    public String AdRefused(Model model, @PathVariable Long adId) {
        Ads ad = adsRepo.getReferenceById(adId);
        ad.setStatus(adStatus.REFUSED);
        adsRepo.save(ad);
        AddAttributesAdAdd(model);
        return "redirect:/ads/{adId}";
    }

    @GetMapping("/waiting/{adId}")
    public String AdWaiting(Model model, @PathVariable Long adId) {
        Ads ad = adsRepo.getReferenceById(adId);
        ad.setStatus(adStatus.WAITING);
        adsRepo.save(ad);
        AddAttributesAdAdd(model);
        return "redirect:/ads/{adId}";
    }

    @GetMapping("/sold/{adId}")
    public String AdSold(Model model, @PathVariable Long adId) {
        Ads ad = adsRepo.getReferenceById(adId);
        ad.setStatus(adStatus.SOLD);
        requestsRepo.deleteAllInBatch(ad.getRequests());
        adsRepo.save(ad);
        AddAttributesAdAdd(model);
        return "redirect:/ads/{adId}";
    }

    @GetMapping("/apps")
    public String AdApps(Model model) {
        AddAttributesAdApps(model);
        return "apps";
    }

    @GetMapping("/my")
    public String AdMy(Model model) {
        AddAttributesAdMy(model);
        return "my";
    }

    @GetMapping("/edit/{id}")
    public String AdEdit(Model model, @PathVariable Long id) {
        AddAttributesAdEdit(model, id);
        return "ad_edit";
    }

    @GetMapping("/delete/{id}")
    public String AdDelete(@PathVariable Long id) {
        adsRepo.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/requests/add/{id}")
    public String AdRequestAdd(@PathVariable Long id, @RequestParam String message, @RequestParam String tel) {
        requestsRepo.save(new Requests(adsRepo.getReferenceById(id), getUser(), message, tel));
        return "redirect:/ads/{id}";
    }

    @GetMapping("/requests/{id}")
    public String AdRequests(Model model, @PathVariable Long id) {
        AddAttributes(model);
        model.addAttribute("requests", adsRepo.getReferenceById(id).getRequests());
        return "requests";
    }

    @PostMapping("/add")
    public String AdAddNew(Model model, @RequestParam String name, @RequestParam MultipartFile[] photos, @RequestParam MultipartFile file, @RequestParam Category category, @RequestParam String description, @RequestParam Type type, @RequestParam Region region, @RequestParam String tel, @RequestParam Long pricelistId) {
        String[] result_photos = new String[0];
        try {
            if (photos != null && !Objects.requireNonNull(photos[0].getOriginalFilename()).isEmpty()) {
                String uuidFile = UUID.randomUUID().toString();
                result_photos = new String[photos.length];
                for (int i = 0; i < result_photos.length; i++) {
                    String result_screenshot = "files/" + uuidFile + "_" + photos[i].getOriginalFilename();
                    photos[i].transferTo(new File(uploadImg + "/" + result_screenshot));
                    result_photos[i] = result_screenshot;
                }
            }
        } catch (Exception e) {
            AddAttributesAdAdd(model);
            model.addAttribute("message", "Ошибка, некорректный данные!");
            return "ad_add";
        }

        String resultFile = "";
        try {
            if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
                String uuidFile = UUID.randomUUID().toString();
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) uploadDir.mkdir();
                resultFile = "files/" + uuidFile + "_" + file.getOriginalFilename();
                file.transferTo(new File(uploadImg + "/" + resultFile));
            }
        } catch (IOException e) {
            model.addAttribute("message", "Ошибка, некорректный данные!");
            AddAttributesAdAdd(model);
            return "ad_add";
        }

        Ads ad = new Ads(name, tel, description, category, type, region, getUser(), pricelistRepo.getReferenceById(pricelistId));

        ad.setPhotos(result_photos);
        ad.setFile(resultFile);

        ad = adsRepo.save(ad);

        return "redirect:/ads/" + ad.getId();
    }

    @PostMapping("/edit/{id}")
    public String AdEditOld(Model model, @RequestParam String name, @RequestParam MultipartFile[] photos, @RequestParam MultipartFile file, @RequestParam Category category, @RequestParam String description, @RequestParam Type type, @RequestParam Region region, @RequestParam String tel, @RequestParam Long pricelistId, @PathVariable Long id) {
        Ads ad = adsRepo.getReferenceById(id);

        if (photos != null && !Objects.requireNonNull(photos[0].getOriginalFilename()).isEmpty()) {
            try {
                String uuidFile = UUID.randomUUID().toString();
                String[] result_photos = new String[photos.length];
                for (int i = 0; i < result_photos.length; i++) {
                    String result_photo = "files/" + uuidFile + "_" + photos[i].getOriginalFilename();
                    photos[i].transferTo(new File(uploadImg + "/" + result_photo));
                    result_photos[i] = result_photo;
                }
                ad.setPhotos(result_photos);
            } catch (Exception e) {
                AddAttributesAdAdd(model);
                model.addAttribute("message", "Ошибка, некорректный данные!");
                return "ad_edit";
            }
        }

        String resultFile = "";
        try {
            if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
                String uuidFile = UUID.randomUUID().toString();
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) uploadDir.mkdir();
                resultFile = "files/" + uuidFile + "_" + file.getOriginalFilename();
                file.transferTo(new File(uploadImg + "/" + resultFile));
            }
        } catch (IOException e) {
            model.addAttribute("message", "Ошибка, некорректный данные!");
            AddAttributesAdAdd(model);
            return "ad_add";
        }

        ad.set(name, tel, description, category, type, region, pricelistRepo.getReferenceById(pricelistId));

        adsRepo.save(ad);

        return "redirect:/ads/{id}";
    }
}
