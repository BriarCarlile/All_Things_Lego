package com.allthingslego.inventory.storagelocation;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StorageLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String code;
    private String description;
    private Instant created_at;
    private Instant updated_at;

    protected StorageLocation() {}

    public StorageLocation(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return String.format("StorageLocation[id=%d, code='%s', description='%s']", 
                this.id, this.code, this.description);
    }
}
