package com.allthingslego.inventory.ownedset;

import java.time.Instant;
import java.time.LocalDate;

import jakarta.persistence.Column;
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
    private Long id;
    
    @Column(name = "catalog_set_id")
    private Long catalogSetId;
    
    @Column(name = "storage_location_id")
    private Long storageLocationId;
    
    @Column(name = "quantity_owned")
    private int quantityOwned;

    @Column(name = "set_condition")
    private String setCondition;
    
    @Column(name = "purchase_date")
    private LocalDate purchaseDate;
    
    @Version
    private int version;
    
    @Column(name = "created_at")
    private Instant createdAt;
    
    @Column(name = "updated_at")
    private Instant updatedAt;

    protected OwnedSet() {}

    public OwnedSet(Long catalogSetId) {
        this.catalogSetId = catalogSetId;
    }

    @Override
    public String toString() {
        return String.format("OwnedSet[id=%d, catalogSetId=%d, quantity=%d, condition='%s']", 
                this.id, this.catalogSetId, this.quantityOwned, this.setCondition);
    }
}
