package com.allthingslego.inventory.ownedset;

import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;

@Projection(name="ownedSetInfo", types=OwnedSet.class)
public interface OwnedSetDTO {
    Long getId();
    Long getCatalogSetId();
    Long getStorageLocationId();
    int getQuantity();
    String getCondition();
    LocalDate getPurchaseDate();
}
