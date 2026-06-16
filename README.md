# no-distractions
Minecraft mod to remove distractions from the building aspect of Minecraft (partially inspired by ["Another serious critique of Minecraft"](https://www.youtube.com/watch?v=_KqeLT-EOe0)).

1) Removed villages (no free houses, food or trading)
2) Reworked night
    - Removed skipping the night by sleeping (no skipping hostile mobs, no being pestered by other people to sleep or rejoin every 5 seconds in multiplayer).
    - Disabled vanilla phantom spawns.
    - You start and respawn with full health, but sprint is disabled if you are missing health.
    - You can only set your spawn if there are no monsters nearby or in the daytime (doesn't work on Forge/Neoforge).
3) Reworked hunger
    - You start and respawn with 10/20 hunger and some saturation.
    - You can lose health, hunger and saturation on peaceful difficulty.
    - You can eat food if you are either missing health, missing hunger, or have zero saturation.
    - You only lose hunger if you are either missing health, missing hunger, or you have a harmful potion effect.
    - You lose saturation normally.

Optionally, you can also install [AppleSkin](https://www.curseforge.com/minecraft/mc-mods/appleskin) (and disable showing the exhaustion) to show the saturation.

Available on [CurseForge](https://www.curseforge.com/minecraft/mc-mods/no-distractions) and [Modrinth](https://modrinth.com/mod/no-distractions).

TODO: forge version

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
  b) https://files.minecraftforge.net/net/minecraftforge/forge/
  c) https://neoforged.net/mod-generator/
    Mod Name="ExampleMod"
    Package Name="com.examplemod"
    Minecraft Version=...
    Mod Authors="Me!"
    Mod Description="Description"
    Advanced Options.Add mixin configuration=true
```
`ice list` to list versions \
`ice <fabric|forge|neoforge> <mc_version>` to change to the selected version \
`ice run` or Open `./current` in IntelliJ IDEA and run `runClient` gradle task \
`ice build-version <version>` to run and build the selected version