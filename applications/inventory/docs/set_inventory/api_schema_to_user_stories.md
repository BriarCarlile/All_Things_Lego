# Inventory Acquisition & Lifecycle

## Add Set by Barcode

```
POST /api/inventory/scan
```

Body:

```
{
  "barcode": "1234567890",
  "storageLocationId": 3
}
```

Maps to:

* Add set by barcode
* Add additional copy
* Each physical box is distinct

Notes:

* Creates new OwnedSet
* Creates InventoryEvent(CREATED)
* Triggers CatalogSet ensureExists()

---

## Add Set Manually

```
POST /api/inventory/manual
```

Body:

```
{
  "bricklinkNo": "75313-1",
  "storageLocationId": 3,
  "condition": "SEALED"
}
```

Maps to:

* Manually add a set
* Add additional copies

---

## Update Condition (Build/Open)

```
PATCH /api/inventory/{ownedSetId}/condition
```

Body:

```
{
  "condition": "BUILT"
}
```

Maps to:

* Update condition
* Lifecycle state changes

Creates:

* InventoryEvent(CONDITION_CHANGED)

---

## Mark as SOLD

```
PATCH /api/inventory/{ownedSetId}/sell
```

No body required.

Maps to:

* Mark set as SOLD instead of delete
* Preserve historical ownership

Creates:

* InventoryEvent(SOLD)

---

## Get Inventory Overview

```
GET /api/inventory
```

Query params:

* condition
* storageLocationId
* search

Maps to:

* View total sets
* Filter by condition
* Filter by storage
* Search by name or set number

---

## Get Inventory Item Details

```
GET /api/inventory/{ownedSetId}
```

Maps to:

* View detailed information about a specific set

---

# Storage Management

## Create Storage Location

```
POST /api/storage-locations
```

Maps to:

* Create new storage location

---

## Update Storage Location

```
PATCH /api/storage-locations/{id}
```

Maps to:

* Rename or update storage location

---

## Delete Storage Location

```
DELETE /api/storage-locations/{id}
```

Behavior:

* Reject if any active OwnedSet references it

Maps to:

* Safe delete storage location

---

## Move Set Between Locations

```
PATCH /api/inventory/{ownedSetId}/move
```

Body:

```
{
  "storageLocationId": 5
}
```

Maps to:

* Move a set by scanning
* Storage updates

Creates:

* InventoryEvent(MOVED)

---

# Validation Workflows

This is workflow territory — not CRUD.

## Start Validation Session

```
POST /api/validation-sessions
```

Optional body:

```
{
  "storageLocationId": 3
}
```

Maps to:

* Start validation session
* Location-based validation

---

## Scan During Validation

```
POST /api/validation-sessions/{sessionId}/scan
```

Body:

```
{
  "barcode": "1234567890"
}
```

Maps to:

* Scan multiple sets
* Bulk validation

---

## Get Validation Results

```
GET /api/validation-sessions/{sessionId}/results
```

Maps to:

* See missing sets
* See unexpected sets
* See discrepancies

---

# Catalog Sync

## Get Catalog Set

```
GET /api/catalog/{bricklinkNo}
```

Behavior:

* Triggers ensureExists()
* Returns catalog metadata

Maps to:

* Automatic metadata fetch
* Sync if stale

---

## Force Refresh Catalog

```
POST /api/catalog/{bricklinkNo}/refresh
```

Maps to:

* Manual refresh if needed

---

# Audit / Events

## Get Events for OwnedSet

```
GET /api/inventory/{ownedSetId}/events
```

Maps to:

* View history of changes

---

# Inventory Insights

You can support these via query parameters on GET /inventory

Or create dedicated endpoints:

```
GET /api/inventory/stats
```

Returns:

* totalOwned
* totalSealed
* totalBuilt
* totalSold

Maps to:

* Inventory insights

---

# Endpoint Count Summary

Core endpoints (without overengineering):

* 6 inventory endpoints
* 3 storage endpoints
* 3 validation endpoints
* 2 catalog endpoints
* 1 events endpoint
* 1 stats endpoint

Total ≈ 16 endpoints

---