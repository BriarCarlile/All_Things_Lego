package com.allthingslego.inventory.ownedset;

import java.time.Instant;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OwnedSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private int catalog_set_id;
    private int storage_location_id;
    private int quantity_owned;
    private String condition;
    private LocalDate purchase_date;
    
    @Version
    private int version;
    
    private Instant created_at;
    private Instant updated_at;

    protected OwnedSet() {}

    public OwnedSet(int catalog_set_id) {
        this.catalog_set_id = catalog_set_id;
    }

    @Override
    public String toString() {
        return String.format("OwnedSet[id=%d, catalog_set_id=%d, quantity=%d, condition='%s']", 
                this.id, this.catalog_set_id, this.quantity_owned, this.condition);
    }
}
