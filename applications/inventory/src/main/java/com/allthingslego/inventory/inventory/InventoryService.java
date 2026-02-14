package com.allthingslego.inventory.inventory;


import com.allthingslego.inventory.barcodemapping.BarcodeMapping;
import com.allthingslego.inventory.barcodemapping.BarcodeMappingRepository;
import com.allthingslego.inventory.catalogset.CatalogSet;
import com.allthingslego.inventory.catalogset.CatalogSetRepository;
import com.allthingslego.inventory.inventory.dto.OwnedSetDetailResponseDTO;
import com.allthingslego.inventory.ownedset.OwnedSet;
import com.allthingslego.inventory.ownedset.OwnedSetRepository;
import com.allthingslego.inventory.storagelocation.StorageLocation;
import com.allthingslego.inventory.storagelocation.StorageLocationRepository;
import com.allthingslego.inventory.structures.SetCondition;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {

    private final OwnedSetRepository ownedSetRepository;
    private final CatalogSetRepository catalogSetRepository;
    private final StorageLocationRepository storageLocationRepository;
    private final BarcodeMappingRepository barcodeMappingRepository;

    @Transactional
    public OwnedSetDetailResponseDTO getOwnedSetDetailsByOwnedSetID(Long ownedSetId) {
        OwnedSet ownedSet = ownedSetRepository.findById(ownedSetId)
            .orElseThrow(() -> new RuntimeException("Could not find owned set"));
        return buildOwnedSetDetailResponseDTO(ownedSet);
    }

    @Transactional
    public OwnedSetDetailResponseDTO getOwnedSetDetailsByBarcode(String barcode) {

        BarcodeMapping barcodeMapping = barcodeMappingRepository.findByBarcode(barcode)
            .orElseThrow(() -> new RuntimeException("Could not find barcode mapping"));

        OwnedSet ownedSet = ownedSetRepository.findByCatalogSetId(barcodeMapping.getCatalogSetId())
            .orElseThrow(() -> new RuntimeException("Could not find owned set"));

        return buildOwnedSetDetailResponseDTO(ownedSet);
    }

    @Transactional
    public List<OwnedSetDetailResponseDTO> getFilteredOwnedSets(SetCondition setCondition, Long storageLocationId, String search) {
        if (storageLocationId == null && search == null && setCondition == null) {
            List<OwnedSetDetailResponseDTO> results = new ArrayList<>();
            Iterable<OwnedSet> iterator = ownedSetRepository.findAll();
            for (OwnedSet ownedSet : iterator) {
                results.add(buildOwnedSetDetailResponseDTO(ownedSet));
            }
            return results;
        } else {
            List<OwnedSetDetailResponseDTO> results = new ArrayList<>();
            List<OwnedSet> storageLocationIdResults = new ArrayList<>(List.of());
            List<OwnedSet> setConditionResults = new ArrayList<>(List.of());
            List<OwnedSet> searchResults = new ArrayList<>(List.of());
            if (storageLocationId != null) {
                storageLocationIdResults.addAll(ownedSetRepository.findByStorageLocationId(storageLocationId));
            }
            if (setCondition != null) {
                setConditionResults.addAll(ownedSetRepository.findBySetCondition(setCondition));
            }
            if (search != null) {
                searchResults.addAll(catalogSetRepository.searchCatalogSetByBricklinkNoOrName(search, search));
            }
            List<OwnedSet> ownedSetsResults = new ArrayList<>(storageLocationIdResults);
            ownedSetsResults.retainAll(setConditionResults);
            ownedSetsResults.retainAll(searchResults);

            for (OwnedSet ownedSet : ownedSetsResults) {
                results.add(buildOwnedSetDetailResponseDTO(ownedSet));
            }
            return results;
        }
    }

    private OwnedSetDetailResponseDTO buildOwnedSetDetailResponseDTO(OwnedSet ownedSet) {
        CatalogSet catalogSet = catalogSetRepository.findById(ownedSet.getCatalogSetId())
            .orElseThrow(() -> new RuntimeException("Could not find catalog set"));

        StorageLocation location = null;
        if (ownedSet.getStorageLocationId() != null) {
            location = storageLocationRepository.findById(ownedSet.getStorageLocationId())
                .orElse(null);
        }
        return new OwnedSetDetailResponseDTO(ownedSet.getId(),
            ownedSet.getSetCondition(),
            ownedSet.getPurchaseDate(),
            ownedSet.getSoldAt(),
            ownedSet.getAcquisitionMethod(),
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
