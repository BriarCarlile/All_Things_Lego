package com.allthingslego.inventory.structures;

public enum SetCondition {
    SEALED,
    OPENED, // Reflects a set that is opened but not being build. (i.e. Broke the seal for some reason)
    BUILT,
    IN_BUILD, // Reflects a set that is opened and being built
    SOLD
}
