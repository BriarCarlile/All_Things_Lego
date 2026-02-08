package com.allthingslego.inventory.catalogset;


import org.springframework.data.rest.core.config.Projection;

@Projection(name="setInfo", types={CatalogSet.class})
public interface CatalogSetDTO {
    Long getId();
    String getBricklinkNo();
    String getName();
    int getYearReleased();
    String getTheme();
}
