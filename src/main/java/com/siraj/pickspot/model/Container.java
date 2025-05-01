package com.siraj.pickspot.model;

public record Container(
        String id,
        String size,
        boolean needsCold,
        int x,
        int y
) { }
