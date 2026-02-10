package com.allthingslego.inventory.inventory;


import com.allthingslego.inventory.inventory.dto.OwnedSetDetailResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @GetMapping("/owned-sets/{id}")
    public ResponseEntity<OwnedSetDetailResponseDTO> getOwnedSetDetails(@PathVariable Long id) {
        log.info("Retrieving owned set details for {}", id);
        OwnedSetDetailResponseDTO details = inventoryService.getOwnedSetDetails(id);
        return ResponseEntity.ok(details);
    }
}
