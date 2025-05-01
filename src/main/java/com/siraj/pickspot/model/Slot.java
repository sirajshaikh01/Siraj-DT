package com.siraj.pickspot.model;

public record Slot(
        int x,
        int y,
        String sizeCap,
        boolean hasColdUnit,
        boolean occupied
) {}
