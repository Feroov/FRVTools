package com.feroov.frv.entities.variants;

import java.util.Arrays;
import java.util.Comparator;

public enum CroakerVariant
{
    BLUE(0),
    ORANGE(1),
    GREEN(2),
    YELLOW(3),
    RED(4),
    BLACK(5);

    private static final CroakerVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(CroakerVariant::getId)).toArray(CroakerVariant[]::new);
    private final int id;

    CroakerVariant(int p_30984_)
    {
        this.id = p_30984_;
    }

    public int getId()
    {
        return this.id;
    }

    public static CroakerVariant byId(int id)
    {
        return BY_ID[id % BY_ID.length];
    }
}