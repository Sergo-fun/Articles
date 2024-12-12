package com.articles.repo;

import com.articles.model.Pricelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricelistRepo extends JpaRepository<Pricelist, Long> {
}
