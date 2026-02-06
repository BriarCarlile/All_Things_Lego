# 0001 – Inventory Domain Model and Catalog Caching Strategy

## Status

Accepted

## Date

2026-02-06

---

## Context

This application is a personal LEGO inventory tracker designed to:

* Track LEGO sets owned
* Track quantity and storage location
* Support barcode-based set addition
* Practice production-level backend architecture patterns

Initially, the system design considered tightly coupling:

* Inventory storage
* Full BrickLink catalog mirroring
* A recoloring feature based on part availability

Further analysis revealed:

1. The recoloring feature is a separate domain concern.
2. Fully mirroring the BrickLink catalog introduces unnecessary complexity.
3. The inventory use case primarily requires stable set metadata, not full catalog replication.

The system must balance:

* Simplicity appropriate for a personal project
* Clean domain separation
* Production-grade design practices
* Extensibility for future features

---

## Decision

### 1. Separate Inventory from Catalog Concerns

Two distinct conceptual domains are defined:

**Inventory Domain**
Tracks ownership state (quantity, location, condition).

**Catalog Cache Domain**
Stores minimal set metadata required for display and reference.

The recoloring feature is intentionally excluded from the inventory schema and will be implemented as a separate bounded context in the future.

---

### 2. Use a Cached CatalogSet Model

Instead of fully mirroring the BrickLink catalog, the system will:

* Cache metadata only for sets that are added to the inventory.
* Store:

  * `bricklink_no`
  * `name`
  * `year_released`
  * `theme`
  * `image_url`
  * `last_synced_at`
  * `sync_status`

This avoids:

* Large-scale catalog storage
* Complex synchronization logic
* Unnecessary database growth

---

### 3. Introduce Sync Metadata

Each `CatalogSet` will contain:

* `last_synced_at`
* `sync_status` (SYNCED, STALE, ERROR)

The system will:

* Serve cached data immediately
* Refresh data only if stale (for example, older than 30 days)
* Avoid blocking user operations during sync failures

This mirrors production systems that favor availability over strict real-time consistency.

---

### 4. Separate Ownership State from Reference Data

`OwnedSet` references `CatalogSet` via a foreign key.

This enables:

* Clean relational integrity
* Multiple owned entries per set (if desired)
* Future wishlist or multi-user support
* Expansion into SaaS-style architecture patterns

---

### 5. Normalize Storage Locations

Storage locations are modeled as a separate entity:

`StorageLocation`

This prevents:

* String duplication
* Inconsistent naming
* Difficult indexing

This aligns with standard database normalization practices.

---

### 6. Include Optimistic Locking

Both `CatalogSet` and `OwnedSet` include a `version` column.

This allows:

* Optimistic locking
* Safe concurrent updates
* Practice of enterprise-grade persistence patterns

---

## Alternatives Considered

### Full BrickLink Catalog Mirroring

**Pros**

* Complete local data
* No external dependency during runtime

**Cons**

* Increased schema complexity
* Sync maintenance burden
* Storage overhead
* Premature optimization for a personal tool

This option was rejected due to unnecessary complexity relative to project scope.

---

### Fully API-Driven (No Caching)

**Pros**

* Minimal database
* Always up-to-date data

**Cons**

* External API dependency at runtime
* Rate limiting risk
* Slower user experience
* Reduced resilience

This option was rejected due to reliability concerns and lack of production realism.

---

## Consequences

### Positive

* Clean bounded context separation
* Production-style architecture patterns
* Extensible data model
* Resilient sync strategy
* Clear relational integrity
* Maintainable long-term structure

### Tradeoffs

* Slightly more schema complexity than a minimal design
* Requires background sync logic
* Cached data may become temporarily stale

These tradeoffs are acceptable for the goals of the project.

---

## Future Considerations

* Recolor feature implemented as a separate domain model
* Multi-user support
* Wishlist tracking
* Event-based audit logging for inventory changes
* Scheduled sync job
* Migration toward modular service boundaries (inventory service vs catalog client)

---

## Summary

The chosen design:

* Optimizes for clarity over premature optimization
* Separates ownership state from reference data
* Uses caching to balance performance and simplicity
* Applies production-grade persistence and integrity patterns

This approach supports both the practical goals of a personal inventory tracker and the educational goal of practicing scalable system design principles.
