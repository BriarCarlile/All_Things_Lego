package com.allthingslego.inventory.catalogset;

import java.time.Instant;

import com.allthingslego.inventory.structures.SyncStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class CatalogSet {

    @Id
    @SequenceGenerator(
            name = "catalogSet_sequence",
            sequenceName = "catalogSet_sequence",
            allocationSize = 1
    )
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "catalogSet_sequence"
    )
    private Long id;
    
    @Column(name = "bricklink_no")
    private String bricklinkNo;
    
    private String name;
    
    @Column(name = "year_released")
    private int yearReleased;
    
    private String theme;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @Column(name = "last_synced_at")
    private Instant lastSyncedAt;
    
    @Column(name = "sync_status")
    private SyncStatus syncStatus;
    
    @Version
    private int version;
    
    @Column(name = "created_at")
    private Instant createdAt;
    
    @Column(name = "updated_at")
    private Instant updatedAt;

    protected CatalogSet() {}

    public CatalogSet(String bricklinkNo) {
        this.bricklinkNo = bricklinkNo;
    }

    @Override
    public String toString() {
        return String.format("CatalogSet[id=%d, bricklinkNo='%s', name='%s', year=%d, theme='%s', syncStatus='%s']",
                this.id, this.bricklinkNo, this.name, this.yearReleased, this.theme, this.syncStatus);
    }

}