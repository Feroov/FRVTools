package com.feroov.frv.entities.variants;

import java.util.Arrays;
import java.util.Comparator;

public enum FlintlockerVariant
{
    MAIN(0),
    FLINT2(1),
    FLINT3(2),
    FLINT4(3),
    FLINT5(4),
    FLINT6(5);

    private static final FlintlockerVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(FlintlockerVariant::getId)).toArray(FlintlockerVariant[]::new);
    private final int id;

    FlintlockerVariant(int p_30984_)
    {
        this.id = p_30984_;
    }

    public int getId()
    {
        return this.id;
    }

    public static FlintlockerVariant byId(int id)
    {
        return BY_ID[id % BY_ID.length];
    }
}