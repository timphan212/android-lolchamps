package com.tutorial.tim.lolchamps;

import java.util.List;

/**
 * Created by Tim on 6/17/2015.
 */
public class Champions {
    public static class ChampionList {
        List<ChampionInfo> champions;
    }

    public static class ChampionInfo {
        boolean active;
        boolean botEnabled;
        boolean botMmEnabled;
        boolean freeToPlay;
        long id;
        boolean rankedPlayEnabled;
    }
}

