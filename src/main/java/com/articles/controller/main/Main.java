package com.articles.controller.main;

import com.articles.model.Users;
import com.articles.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Main {

    @Autowired
    protected UsersRepo usersRepo;
    @Autowired
    protected AdsRepo adsRepo;
    @Autowired
    protected CommentsRepo commentsRepo;
    @Autowired
    protected RequestsRepo requestsRepo;
    @Autowired
    protected PricelistRepo pricelistRepo;

    protected String uploadImg = uploadPath + "/img/";

    public static String uploadPath = getUploadPath();

    private static String getUploadPath() {
        StringBuilder dir = new StringBuilder(System.getProperty("user.dir"));
        for (int j = 0; j < dir.length(); j++) {
            if (dir.charAt(j) == '\\') {
                dir.setCharAt(j, '/');
            }
        }

        dir.append("/src/main/resources");

        return dir.toString();
    }

    public static String saveFile(MultipartFile file, String path) throws IOException {
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            String result = path + "/" + uuidFile + "_" + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/img/" + result));
            return "/img/" + result;
        } else throw new IOException();
    }

    protected String DateNow() {
        return LocalDateTime.now().toString().substring(0, 10);
    }

    protected Users getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return usersRepo.findByUsername(userDetail.getUsername());
        }
        return null;
    }

    protected String getRole() {
        Users users = getUser();
        if (users == null) return "NOT";
        return users.getRole().toString();
    }

    public static float round(float value) {
        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        return (float) tmp / factor;
    }
}