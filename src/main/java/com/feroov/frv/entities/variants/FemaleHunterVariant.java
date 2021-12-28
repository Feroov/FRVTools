package com.feroov.frv.entities.variants;

import java.util.Arrays;
import java.util.Comparator;

public enum FemaleHunterVariant
{
    MAIN(0),FEMHUNTER2(1),FEMHUNTER3(2),FEMHUNTER4(3),FEMHUNTER5(4),FEMHUNTER6(5),;

    private static final FemaleHunterVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(FemaleHunterVariant::getId)).toArray(FemaleHunterVariant[]::new);
    private final int id;

    FemaleHunterVariant(int p_30984_)
    {
        this.id = p_30984_;
    }

    public int getId()
    {
        return this.id;
    }

    public static FemaleHunterVariant byId(int id)
    {
        return BY_ID[id % BY_ID.length];
    }
}