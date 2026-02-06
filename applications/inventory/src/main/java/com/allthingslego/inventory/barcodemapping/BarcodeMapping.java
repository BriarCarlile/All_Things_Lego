package com.allthingslego.inventory.barcodemapping;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BarcodeMapping {

    @Id
    private String barcode;
    
    @Column(name = "catalog_set_id")
    private Long catalogSetId;
    
    @Column(name = "last_synced_at")
    private Instant lastSyncedAt;

    protected BarcodeMapping() {}

    public BarcodeMapping(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return String.format("BarcodeMapping[barcode='%s', catalogSetId=%d]",
                this.barcode, this.catalogSetId);
    }
}
