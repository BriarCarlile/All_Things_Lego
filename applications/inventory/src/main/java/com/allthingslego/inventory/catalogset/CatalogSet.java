package com.allthingslego.inventory.catalogset;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
    private int id;
    private String bricklink_no;
    private String name;
    private int year_released;
    private String theme;
    private String image_url;
    private Instant last_synced_at;
    private String sync_status;
    private int version;
    private Instant created_at;
    private Instant updated_at;

    protected CatalogSet() {}

    public CatalogSet(String bricklink_no) {
        this.bricklink_no = bricklink_no;
    }

    @Override
    public String toString() {
        return String.format("CatalogSet[id=%d, bricklink_no='%s', name='%s', year=%d, theme='%s', sync_status='%s']",
                this.id, this.bricklink_no, this.name, this.year_released, this.theme, this,sync_status);
    }

}