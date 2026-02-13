# User Stories

These user stories define the expected behaviors of the LEGO Inventory application.
They guide backend API design, domain modeling, and frontend implementation.

---

# Inventory Acquisition & Lifecycle

## Add Sets

* As a user, I want to add a LEGO set to my inventory by scanning its barcode.
* As a user, I want to manually add a LEGO set to my inventory by entering its set number.
* As a user, I want to add additional copies of a set I already own so that each physical box is tracked individually.
* As a user, I want each physical LEGO box to be represented as a distinct inventory record.

## Update Set State

* As a user, I want to update a set’s condition (e.g., sealed, opened, built).
* As a user, I want to view the acquisition timestamp for a specific set.
* As a user, I want to mark a set as SOLD instead of deleting it so that historical ownership is preserved.
* As a user, I want to view the history of changes made to a specific set.

---

# Inventory Validation

## Bulk Validation Session

* As a user, I want to start a validation session to verify my inventory.
* As a user, I want to scan multiple sets during a validation session.
* As a user, I want to see which expected sets were not scanned.
* As a user, I want to see which scanned sets are not registered in my inventory.
* As a user, I want validation results to clearly show discrepancies.

## Location-Based Validation

* As a user, I want to validate all sets within a specific storage location.
* As a user, I want to see missing or unexpected sets within a storage location.

---

# Storage Management

* As a user, I want to create a new storage location with a name and optional barcode.
* As a user, I want to rename or update a storage location.
* As a user, I want to delete a storage location only if no sets are currently assigned to it.
* As a user, I want to move a set from one storage location to another by scanning the set.

---

# Catalog Synchronization

* As a user, I want the system to automatically fetch catalog metadata if a set does not exist locally.
* As a user, I want catalog data to refresh if it becomes outdated.
* As a user, I want to see when catalog data was last synchronized.
* As a user, I want the system to gracefully handle failures when external catalog data cannot be retrieved.

---

# Inventory Insights & Search

* As a user, I want to see how many total sets I own.
* As a user, I want to see how many sets I own by condition (sealed, opened, built).
* As a user, I want to filter my inventory by storage location.
* As a user, I want to search my inventory by set number or name.
* As a user, I want to view detailed information about a specific set in my inventory.

---

# Data Integrity & Error Handling

* As a user, I want duplicate barcode scans to not accidentally create duplicate records unless intentionally adding a new physical box.
* As a user, I want clear feedback when a barcode is not recognized.
* As a user, I want invalid operations (e.g., deleting a storage location with sets assigned) to be safely prevented with meaningful error messages.

---

# Auditability

* As a user, I want inventory changes (added, moved, removed, updated) to be recorded.
* As a user, I want to view an audit history for a specific inventory item.

---

# Representation Model

* Each physical LEGO box is represented as a distinct inventory record.
* Inventory quantity is derived from the count of physical records, not stored as a numeric aggregate.

