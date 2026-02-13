package com.allthingslego.inventory.inventoryevent;

import com.allthingslego.inventory.structures.EventType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Setter
@Getter
public class InventoryEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owned_set_id")
    private Long ownedSetId;

    @Column(name = "event_type")
    private EventType eventType;

    @Column(name = "details")
    private String details;

    @Column(name = "created_at")
    private Instant createdAt;
}
