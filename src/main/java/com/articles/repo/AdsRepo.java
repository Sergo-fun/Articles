package com.articles.repo;

import com.articles.model.Ads;
import com.articles.model.enums.adStatus;
import com.articles.model.enums.Category;
import com.articles.model.enums.Region;
import com.articles.model.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdsRepo extends JpaRepository<Ads, Long> {
    List<Ads> findAllByStatus(adStatus status);

    List<Ads> findAllByNameContainingAndStatusAndCategoryAndRegionAndType(String name, adStatus status, Category category, Region region, Type type);

    List<Ads> findAllByStatusAndCategory(adStatus status, Category category);

    List<Ads> findAllByStatusAndType(adStatus status, Type type);

    List<Ads> findAllByStatusAndRegion(adStatus status, Region region);
}
