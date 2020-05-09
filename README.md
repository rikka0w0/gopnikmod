# The Gopnik Mod
водка(Vodka), семечки(Sunflower Seeds), and more! The most SLAVIC Minecraft Mod! 

Supported Minecraft Version: 1.15.2

# Dependencies
[LibRikka](https://github.com/rikka0w0/librikka)

# Compiling and Testing
1. Ensure that `Java` (found [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)), `Git` (found [here](http://git-scm.com/)) are installed correctly on your system.
1. Create a base directory for the repo
1. (On Windows) open Git CMD and navigate to the directory just created
1. `git clone https://github.com/rikka0w0/gopnikmod`
1. `git submodule init` and `git submodule update` to get LibRikka
1. `gradlew build` to build jars
1. `gradlew genEclipseRuns` to setup a complete development environment.
* On Windows: use `gradlew.bat` instead of `gradlew`

# Notes
1. If you are using Intellij Idea, the IDE will configure LibRikka automatically, so you don't need to worry about this
2. Obfuscated and deobfuscated jars needs LibRikka to work properly
3. Navigate to librikka directory and use `gradlew build' to build LibRikka
4. In standalone MineCraft, you need to have both `gopnikmod.jar` and `librikka.jar` in your `mods` folder
5. `gopnikmod-full.jar` includes LibRikka