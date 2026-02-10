package com.allthingslego.inventory.inventory.dto;

import java.time.LocalDate;

public record OwnedSetDetailResponseDTO(
        Long id,
        int quantityOwned,
        String condition,
        LocalDate purchaseDate,
        CatalogSetInfo catalogSet,
        StorageLocationInfo storageLocation
) {
    public record CatalogSetInfo(
        Long id,
        String bricklinkNo,
        String name,
        int yearReleased,
        String theme,
        String imageUrl,
        String syncStatus
    ) {}

    public record StorageLocationInfo(
        Long id,
        String code,
        String description
    ) {}
}
