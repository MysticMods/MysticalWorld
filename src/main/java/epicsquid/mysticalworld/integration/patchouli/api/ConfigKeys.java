package epicsquid.mysticalworld.integration.patchouli.api;

import epicsquid.mysticalworld.config.ConfigManager;
import vazkii.patchouli.api.PatchouliAPI;

public class ConfigKeys {
  public static void init() {
    // Entities

    PatchouliAPI.instance.setConfigFlag("roots:deer", ConfigManager.mobs.spawnDeer);
    PatchouliAPI.instance.setConfigFlag("roots:fox", ConfigManager.mobs.spawnFox);
    PatchouliAPI.instance.setConfigFlag("roots:beetle", ConfigManager.mobs.spawnBeetle);
    PatchouliAPI.instance.setConfigFlag("roots:frog", ConfigManager.mobs.spawnFrog);
    // Sprouts can no longer be disabled.
    PatchouliAPI.instance.setConfigFlag("roots:sprout", true);
    PatchouliAPI.instance.setConfigFlag("roots:endermini", ConfigManager.mobs.spawnEndermini);
    PatchouliAPI.instance.setConfigFlag("roots:owl", ConfigManager.mobs.spawnOwl);
    PatchouliAPI.instance.setConfigFlag("roots:lava_cat", ConfigManager.mobs.spawnLavaCat);

    PatchouliAPI.instance.setConfigFlag("roots:silkworm", ConfigManager.silkworm.enabled);
    PatchouliAPI.instance.setConfigFlag("roots:silkworm_leaves", ConfigManager.silkworm.leafDrops);

    // Ores
    PatchouliAPI.instance.setConfigFlag("roots:amethyst", ConfigManager.amethyst.enableAmethyst);
    PatchouliAPI.instance.setConfigFlag("roots:copper", ConfigManager.copper.enableCopper);
    PatchouliAPI.instance.setConfigFlag("roots:silver", ConfigManager.silver.enableSilver);

    // Tools
    PatchouliAPI.instance.setConfigFlag("roots:amethystAxe", ConfigManager.toolEnable.amethystTools.amethystAxe);
    PatchouliAPI.instance.setConfigFlag("roots:amethystHoe", ConfigManager.toolEnable.amethystTools.amethystHoe);
    PatchouliAPI.instance.setConfigFlag("roots:amethystKnife", ConfigManager.toolEnable.amethystTools.amethystKnife);
    PatchouliAPI.instance.setConfigFlag("roots:amethystPickaxe", ConfigManager.toolEnable.amethystTools.amethystPickaxe);
    PatchouliAPI.instance.setConfigFlag("roots:amethystShovel", ConfigManager.toolEnable.amethystTools.amethystShovel);
    PatchouliAPI.instance.setConfigFlag("roots:amethystSword", ConfigManager.toolEnable.amethystTools.amethystSword);

    PatchouliAPI.instance.setConfigFlag("roots:copperAxe", ConfigManager.toolEnable.copperTools.copperAxe);
    PatchouliAPI.instance.setConfigFlag("roots:copperHoe", ConfigManager.toolEnable.copperTools.copperHoe);
    PatchouliAPI.instance.setConfigFlag("roots:copperKnife", ConfigManager.toolEnable.copperTools.copperKnife);
    PatchouliAPI.instance.setConfigFlag("roots:copperPickaxe", ConfigManager.toolEnable.copperTools.copperPickaxe);
    PatchouliAPI.instance.setConfigFlag("roots:copperShovel", ConfigManager.toolEnable.copperTools.copperShovel);
    PatchouliAPI.instance.setConfigFlag("roots:copperSword", ConfigManager.toolEnable.copperTools.copperSword);

    PatchouliAPI.instance.setConfigFlag("roots:silverAxe", ConfigManager.toolEnable.silverTools.silverAxe);
    PatchouliAPI.instance.setConfigFlag("roots:silverHoe", ConfigManager.toolEnable.silverTools.silverHoe);
    PatchouliAPI.instance.setConfigFlag("roots:silverKnife", ConfigManager.toolEnable.silverTools.silverKnife);
    PatchouliAPI.instance.setConfigFlag("roots:silverPickaxe", ConfigManager.toolEnable.silverTools.silverPickaxe);
    PatchouliAPI.instance.setConfigFlag("roots:silverShovel", ConfigManager.toolEnable.silverTools.silverShovel);
    PatchouliAPI.instance.setConfigFlag("roots:silverSword", ConfigManager.toolEnable.silverTools.silverSword);
  }
}
