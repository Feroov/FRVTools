package com.feroov.frv.entities.variants;

import java.util.Arrays;
import java.util.Comparator;

public enum HunterVariant
{
    MAIN(0);

    private static final HunterVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(HunterVariant::getId)).toArray(HunterVariant[]::new);
    private final int id;

    HunterVariant(int p_30984_)
    {
        this.id = p_30984_;
    }

    public int getId()
    {
        return this.id;
    }

    public static HunterVariant byId(int id)
    {
        return BY_ID[id % BY_ID.length];
    }
}