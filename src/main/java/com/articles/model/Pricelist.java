package com.articles.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Pricelist implements Serializable {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private int days;
    private float price;

    @OneToMany(mappedBy = "pricelist", cascade = CascadeType.ALL)
    private List<Ads> ads = new ArrayList<>();

    public Pricelist(String name, int days, float price) {
        this.name = name;
        this.days = days;
        this.price = price;
    }

    public void set(String name, int days, float price) {
        this.name = name;
        this.days = days;
        this.price = price;
    }
}
