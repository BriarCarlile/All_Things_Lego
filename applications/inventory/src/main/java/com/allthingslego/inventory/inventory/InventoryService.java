package com.allthingslego.inventory.inventory;


import com.allthingslego.inventory.catalogset.CatalogSet;
import com.allthingslego.inventory.catalogset.CatalogSetRepository;
import com.allthingslego.inventory.inventory.dto.OwnedSetDetailResponseDTO;
import com.allthingslego.inventory.ownedset.OwnedSet;
import com.allthingslego.inventory.ownedset.OwnedSetRepository;
import com.allthingslego.inventory.storagelocation.StorageLocation;
import com.allthingslego.inventory.storagelocation.StorageLocationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final OwnedSetRepository ownedSetRepository;
    private final CatalogSetRepository catalogSetRepository;
    private final StorageLocationRepository storageLocationRepository;

    @Transactional
    public OwnedSetDetailResponseDTO getOwnedSetDetails(Long ownedSetId) {
        OwnedSet ownedSet = ownedSetRepository.findById(ownedSetId)
            .orElseThrow(() -> new RuntimeException("Could not find owned set"));

        CatalogSet catalogSet = catalogSetRepository.findById(ownedSet.getCatalogSetId())
            .orElseThrow(() -> new RuntimeException("Could not find catalog set"));

        StorageLocation location = null;
        if (ownedSet.getStorageLocationId() != null) {
            location = storageLocationRepository.findById(ownedSet.getStorageLocationId())
                .orElse(null);
        }

        return new OwnedSetDetailResponseDTO(
            ownedSet.getId(),
            ownedSet.getQuantityOwned(),
            ownedSet.getSetCondition(),
            ownedSet.getPurchaseDate(),
            new OwnedSetDetailResponseDTO.CatalogSetInfo(
                    catalogSet.getId(),
                    catalogSet.getBricklinkNo(),
                    catalogSet.getName(),
                    catalogSet.getYearReleased(),
                    catalogSet.getTheme(),
                    catalogSet.getImageUrl(),
                    catalogSet.getSyncStatus()
            ),
            location != null ? new OwnedSetDetailResponseDTO.StorageLocationInfo(
                location.getId(),
                location.getCode(),
                location.getDescription()
            ) : null
        );
    }
}
