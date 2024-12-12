package com.articles.model;

import com.articles.controller.main.Main;
import com.articles.model.enums.Category;
import com.articles.model.enums.Region;
import com.articles.model.enums.Type;
import com.articles.model.enums.adStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Ads implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private String[] photos;
    private String file;
    private String tel;
    private String date = LocalDateTime.now().toString().substring(0, 10);

    private int counter = 0;

    @Column(length = 5000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Type type;
    @Enumerated(EnumType.STRING)
    private Region region;

    @Enumerated(EnumType.STRING)
    private adStatus status = adStatus.WAITING;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL)
    private List<Comments> comments = new ArrayList<>();
    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL)
    private List<Requests> requests = new ArrayList<>();
    @ManyToOne
    private Users owner;
    @ManyToOne
    private Pricelist pricelist;

    public Ads(String name, String tel, String description, Category category, Type type, Region region, Users owner, Pricelist pricelist) {
        this.name = name;
        this.tel = tel;
        this.description = description;
        this.category = category;
        this.type = type;
        this.region = region;
        this.owner = owner;
        this.pricelist = pricelist;
    }

    public void set(String name, String tel, String description, Category category, Type type, Region region, Pricelist pricelist) {
        this.name = name;
        this.tel = tel;
        this.description = description;
        this.category = category;
        this.type = type;
        this.region = region;
        this.pricelist = pricelist;
    }

    public float getCounterPrice() {
        if (counter == 0) return 0;
        return Main.round(pricelist.getPrice() / counter);
    }

    public float getRequestsPrice() {
        if (requests.isEmpty()) return 0;
        return Main.round(pricelist.getPrice() / requests.size());
    }

    public void addComment(Comments comment) {
        comments.add(comment);
        comment.setAd(this);
    }

    public void removeComment(Comments comment) {
        comments.remove(comment);
        comment.setAd(null);
    }

    public String[] getPartPhotos() {
        return Arrays.copyOfRange(photos, 1, photos.length);
    }
}
