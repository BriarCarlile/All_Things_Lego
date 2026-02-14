package com.allthingslego.inventory.inventory;


import com.allthingslego.inventory.inventory.dto.OwnedSetDetailResponseDTO;
import com.allthingslego.inventory.structures.SetCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/owned-sets/")
    public ResponseEntity<List<OwnedSetDetailResponseDTO>> getFilteredOwnedSets(
            @RequestParam(name = "condition", required = false) SetCondition setCondition,
            @RequestParam(name = "storageLocationId", required = false) Long storageLocationId,
            @RequestParam(name = "search", required = false) String search
            ) {
        log.info("getFilteredOwnedSets: {}, {}, {}", setCondition, storageLocationId, search);
        List<OwnedSetDetailResponseDTO> responses = inventoryService.getFilteredOwnedSets(setCondition, storageLocationId, search);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/owned-sets/{id}")
    public ResponseEntity<OwnedSetDetailResponseDTO> getOwnedSetDetails(@PathVariable Long id) {
        log.info("Retrieving owned set details for {}", id);
        OwnedSetDetailResponseDTO details = inventoryService.getOwnedSetDetailsByOwnedSetID(id);
        return ResponseEntity.ok(details);
    }
}
