# ADR-002: Physical Inventory Modeling and Non-Destructive Removal Strategy

## Status

Accepted

## Context

The LEGO Inventory application is designed to model real-world ownership of physical LEGO sets. During refinement of the user stories and domain model, a key architectural decision was required regarding how inventory should be represented and how set removal should be handled.

Two major design questions emerged:

1. Should inventory be modeled as aggregated quantity (one row per set with a quantity field), or as individual physical units (one row per physical box)?
2. Should removing a set from inventory result in deletion of the database record, or should ownership history be preserved?

The system is intended to be portfolio-quality and reflect production-grade architectural thinking.

---

## Decision

### 1. OwnedSet Represents a Single Physical Unit

Each physical LEGO box is represented as a distinct `OwnedSet` record.

* No `quantity_owned` field is stored.
* Inventory quantity is derived by counting `OwnedSet` rows per `CatalogSet`.
* This enables precise tracking of individual boxes, storage locations, and lifecycle state.

### 2. Non-Destructive Removal via SOLD Condition

Removing a set from inventory will not delete the record.

Instead:

* The `condition` field on `OwnedSet` is updated to `SOLD`.
* An optional `sold_at` timestamp records when the set was sold.
* Active inventory queries exclude records where `condition = SOLD`.

This preserves historical ownership data while keeping current inventory accurate.

---

## Resulting Domain Model Adjustments

### OwnedSet

* One row represents one physical LEGO box.
* `condition` is represented as a controlled enum in the application layer.
* A `sold_at` timestamp is included for auditability.
* Inventory quantity is computed dynamically via count queries.

### Inventory Queries

* Active inventory excludes `OwnedSet` records where `condition = SOLD`.
* Validation sessions ignore SOLD records.
* Storage location relationships remain intact even after sale to preserve historical accuracy.

---

## Consequences

### Positive

* Preserves full ownership history.
* Enables accurate validation workflows.
* Supports future analytics (e.g., ownership duration, sales tracking).
* Prevents accidental data loss.
* Reflects real-world inventory system design.
* Aligns cleanly with barcode-based workflows.
* Demonstrates production-grade modeling practices.

### Trade-offs

* Slightly increased row count due to one-record-per-physical-unit modeling.
* Requires consistent filtering of SOLD records in active inventory queries.

These trade-offs are acceptable given the improved data integrity and extensibility.

---

## Alternatives Considered

### 1. Quantity-Based Inventory Modeling

Rejected because:

* Makes validation sessions more complex.
* Cannot distinguish between individual physical boxes.
* Limits future extensibility (e.g., tracking box condition individually).

### 2. Hard Deletion of OwnedSet Records

Rejected because:

* Loses historical ownership data.
* Prevents future auditability.
* Complicates reporting and analytics.

---

## Future Considerations

* Introduction of an `InventoryEvent` entity for full audit trail.
* Archival filtering for SOLD records in UI.
* Financial tracking fields for resale analytics.
* ValidationSession entity for bulk verification workflows.

---

## Summary

The system models inventory at the level of individual physical units and uses state transitions instead of deletion to represent removal.

This approach prioritizes data integrity, auditability, and extensibility, aligning the LEGO Inventory application with production-quality inventory system design principles.

