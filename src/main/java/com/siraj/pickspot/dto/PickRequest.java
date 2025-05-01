package com.siraj.pickspot.dto;

import com.siraj.pickspot.model.Container;
import com.siraj.pickspot.model.Slot;

import java.util.List;

public record PickRequest(
        Container container,
        List<Slot> yardMap
) {
}
