package com.allthingslego.inventory.inventory.dto;

import com.allthingslego.inventory.structures.SetCondition;
import com.allthingslego.inventory.structures.SyncStatus;

import java.time.LocalDate;

public record OwnedSetDetailResponseDTO(
        Long id,
        SetCondition condition,
        LocalDate purchaseDate,
        LocalDate soldAt,
        String acquisitionMethod,
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
        SyncStatus syncStatus
    ) {}

    public record StorageLocationInfo(
        Long id,
        String code,
        String description
    ) {}
}
