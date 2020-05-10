# The Gopnik Mod
водка(Vodka), семечки(Sunflower Seeds), and more! The most SLAVIC Minecraft Mod! 

Supported Minecraft Version: 1.15.2

![Image](/screenshot.png)

# Dependencies
[LibRikka](https://github.com/rikka0w0/librikka)

# Setup the Environment
1. Ensure `Java` (found [here](https://www.java.com/en/download/manual.jsp)) and `Git` (found [here](http://git-scm.com/)) are properly installed on your system.
1. Create a base directory for the repo (anywhere you like)
1. On Windows, open either 'CMD' or Windows PowerShell, on Linux and MacOS, 
launch a terminal, then navigate to the directory just created,
and type the following commands:
1. `git clone https://github.com/rikka0w0/gopnikmod`
1. `git submodule init` and `git submodule update` to get LibRikka
* On Windows: use `gradlew.bat` instead of `gradlew`

# Build and Test ![Image](/src/main/resources/assets/sime_essential/textures/item/emblem_soviet.png)
1. Complete the steps in "Setup the Environment" section.
1. In the repo root folder, execute `gradlew runData` to launch the data generator, generated resource files will be located at "/src/generated"
1. If you just want to try this mod, run `gradlew runClient` to launch the game, otherwise skip this step.
1. Execute `gradlew build` to build GopnikMod.
1. Switch to the librikka directory under the repo root: `cd librikka`
1. Execute `gradlew build` again to build LibRikka jars
1. Jars files are in `build/libs` and `librikka\build\libs`
*  The suffix of deobfuscated jars is "dev".
*  __Obfuscated jars don't have any suffix, these jars are supposed to be used in normal minecraft games, copy them toyour `.minecraft\mods` directory __
*  `XXXX-full.jar` includes LibRikka

# Notes
For developers, run either `gradlew genIntellijRuns` or `gradlew genEclipseRuns`, then in your IDE, import build.gradle as a gradle project. 
See Minecraft Forge readme for details.