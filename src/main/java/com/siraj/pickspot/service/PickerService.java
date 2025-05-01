package com.siraj.pickspot.service;

import com.siraj.pickspot.model.Container;
import com.siraj.pickspot.model.Slot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PickerService {
    private static final int INVALID = 10_000;

    public Optional<Slot> chooseBestSlot(Container c, List<Slot> yardMap) {
        int bestScore = Integer.MAX_VALUE;
        Slot bestSlot = null;
        for (Slot s : yardMap) {
            int score = score(c, s);
            if (score < bestScore) {
                bestScore = score;
                bestSlot = s;
            }
        }
        if (bestScore >= INVALID) {
            return Optional.empty();
        }
        return Optional.of(bestSlot);
    }

    private int score(Container c, Slot s) {
        int distance = Math.abs(c.x() - s.x()) + Math.abs(c.y() - s.y());

        int sizePenalty = (c.size().equals("big") && s.sizeCap().equals("small")) ? 5 : 0;

        int coldPenalty = (c.needsCold() && !s.hasColdUnit()) ? 3 : 0;

        int occPenalty = s.occupied() ? INVALID : 0;

        return distance + sizePenalty + coldPenalty + occPenalty;
    }
}
