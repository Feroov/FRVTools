package com.feroov.frv.entities.variants;

import java.util.Arrays;
import java.util.Comparator;

public enum PirateVariant
{
    MAIN(0),
    PIRATE2(1),
    PIRATE3(2),
    PIRATE4(3),
    PIRATE5(4),
    PIRATE6(5);

    private static final PirateVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(PirateVariant::getId)).toArray(PirateVariant[]::new);
    private final int id;

    PirateVariant(int p_30984_)
    {
        this.id = p_30984_;
    }

    public int getId()
    {
        return this.id;
    }

    public static PirateVariant byId(int id)
    {
        return BY_ID[id % BY_ID.length];
    }
}