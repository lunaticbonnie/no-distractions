# no-distractions
Minecraft mod to remove distractions from the building aspect of Minecraft (partially inspired by ["Another serious critique of Minecraft"](https://www.youtube.com/watch?v=_KqeLT-EOe0)).

1) Remove villages (no free houses, food or trading)
2) Rework night
    - Remove skipping the night by sleeping (no skipping hostile mobs, no being pestered by other people to sleep or rejoin every 5 seconds in multiplayer).
    - Disable vanilla phantom spawns.
    - You start and respawn with full health, but sprint is disabled if you are missing 1 or more hearts.
    - You can only set your spawn if there are no monsters nearby or in the daytime (doesn't work on Neoforge).
3) Rework hunger
    - You start and respawn with 10/20 hunger and some saturation.
    - You can lose health, hunger and saturation on peaceful difficulty.
    - You can eat food if you are either missing health, missing hunger, or have zero saturation.
    - You only lose hunger if you are either missing health, missing hunger, or you have a harmful potion effect.
    - You lose saturation normally.
4) More building options
    - Allow placing ladders without supporting blocks.

Optionally, you can also install [AppleSkin](https://www.curseforge.com/minecraft/mc-mods/appleskin) (and disable showing the exhaustion) to show the saturation. \
Recommended to use alongside [Stoneholm](https://www.curseforge.com/minecraft/mc-mods/underground-villages-stoneholm) in case that other mods require villagers.

Available on [CurseForge](https://www.curseforge.com/minecraft/mc-mods/no-distractions) and [Modrinth](https://modrinth.com/mod/no-distractions).

## Todo list
- give player regen while sleeping?

## dev
```
Download https://github.com/Patrolin/justice
Download Python 3
Download mod templates for all desired Minecraft versions into `templates/*` from:
  a) https://fabricmc.net/develop/template/
    Mod Name="ExampleMod"
    Package Name="com.examplemod"
    Minecraft Version=...
    Split client and common sources=false
    Rename to `fabric-<mc_version>.zip`
  b) https://files.minecraftforge.net/net/minecraftforge/forge/
    Rename to `forge-<mc_version>-<mdk_version>.zip`
  c) https://neoforged.net/mod-generator/
    Mod Name="ExampleMod"
    Package Name="com.examplemod"
    Minecraft Version=...
    Mod Authors="Me!"
    Mod Description="Description"
    Advanced Options.Add mixin configuration=true
    Rename to `neoforge-<mc_version>.zip`
```
`ice list` to list versions \
`ice <fabric|forge|neoforge> <mc_version>` to change to the selected version \
`ice run` or Open `./current` in IntelliJ IDEA and run `runClient` gradle task \
`ice build-version <version>` to run and build the selected version

## manual forge build
```
// https://github.com/SpongePowered/Mixin/wiki/Mixins-on-Minecraft-Forge + https://github.com/LlamaLad7/MixinExtras
// build.gradle
plugins {
    id 'org.spongepowered.mixin' version '0.7.+'
}
dependencies {
    annotationProcessor 'org.spongepowered:mixin:0.8.5:processor'
    compileOnly(annotationProcessor("io.github.llamalad7:mixinextras-common:0.5.4"))
    implementation(jarJar("io.github.llamalad7:mixinextras-forge:0.5.4")) {
        jarJar.ranged(it, "[0.5.4,)")
    }
}
mixin {
    add sourceSets.main, 'nodistractions.mixins.refmap.json'
    config 'nodistractions.mixins.json'
}
// gradle.properties
...

Copy src/main/java from `ice forge <mc_version>`
Copy src/main/resources from `ice forge <mc_version>`
Delete night rework in ServerPlayerMixin.java
```