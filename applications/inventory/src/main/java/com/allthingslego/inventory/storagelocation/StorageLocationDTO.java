package com.allthingslego.inventory.storagelocation;

import org.springframework.data.rest.core.config.Projection;

import javax.xml.catalog.Catalog;

@Projection(name="storageLocationInfo", types={StorageLocation.class})
public interface StorageLocationDTO {
    Long getId();
    String getCode();
    String getDescription();
}
