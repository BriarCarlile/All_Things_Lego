package com.allthingslego.inventory.barcodemapping;

import java.time.Instant;

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
    
    private int catalog_set_id;
    private Instant last_synced_at;

    protected BarcodeMapping() {}

    public BarcodeMapping(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return String.format("BarcodeMapping[barcode='%s', catalog_set_id=%d]",
                this.barcode, this.catalog_set_id);
    }
}
