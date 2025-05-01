package com.siraj.pickspot.dto;

public record PickResponse(
        String containerId,
        int targetX,
        int targetY
) { }
