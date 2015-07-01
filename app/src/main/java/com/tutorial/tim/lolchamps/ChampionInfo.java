package com.tutorial.tim.lolchamps;

import java.util.List;
import java.util.Map;

/**
 * Created by Tim on 6/30/2015.
 */
public class ChampionInfo {
    public static class ChampionList {
        Map<String, Champion> data;
        String format;
        Map<String, String> keys;
        String type;
        String version;
    }

    public static class Champion {
        List<String> allytips;
        String blurb;
        List<String> enemytips;
        int id;
        ChampImage image;
        ChampInfo info;
        String key;
        String lore;
        String name;
        String partype;
        ChampPassive passive;
        List<ChampRecommended> recommended;
        List<ChampSkins> skins;
        List<ChampSpells> spells;
        ChampStats stats;
        List<String> tags;
        String title;
    }

    public static class ChampSpells {
        List<ChampImage> altimages;
        List<Double> cooldown;
        String cooldownBurn;
        List<Integer> cost;
        String costBurn;
        String costType;
        String description;
        List<List<Double>> effect;
        List<String> effectBurn;
        ChampImage image;
        String key;
        ChampLevelTip leveltip;
        int maxrank;
        String name;
       // String range; Mixed variable type, need to write custom TypeAdapter if want to handle
        String rangeBurn;
        String resource;
        String sanitizedDescription;
        String sanitizedTooltip;
        String tooltip;
        List<ChampSpellVars> vars;
    }

    public static class ChampImage {
        String full;
        String group;
        int h;
        String sprite;
        int w;
        int x;
        int y;
    }

    public static class ChampInfo {
        int attack;
        int defense;
        int difficulty;
        int magic;
    }

    public static class ChampPassive {
        String description;
        ChampImage image;
        String name;
        String sanitizedDescription;
    }

    public static class ChampRecommended {
        List<ChampBlock> blocks;
        String champion;
        String map;
        String mode;
        boolean priority;
        String title;
        String type;
    }

    public static class ChampSkins {
        int id;
        String name;
        int num;
    }

    public static class ChampStats {
        double armor;
        double armorperlevel;
        double attackdamage;
        double attackdamageperlevel;
        double attackrange;
        double attackspeedoffset;
        double attackspeedperlevel;
        double crit;
        double critperlevel;
        double hp;
        double hpperlevel;
        double hpregen;
        double hpregenperlevel;
        double movespeed;
        double mp;
        double mpperlevel;
        double mpregen;
        double mpregenperlevel;
        double spellblock;
        double spellblockperlevel;
    }

    public static class ChampLevelTip {
        List<String> effect;
        List<String> label;
    }

    public static class ChampSpellVars {
        List<Double> coeff;
        String dyn;
        String key;
        String link;
        String ranksWith;
    }

    public static class ChampBlock {
        List<ChampBlockItem> items;
        boolean recMath;
        String type;
    }

    public static class ChampBlockItem {
        int count;
        int id;
    }
}
