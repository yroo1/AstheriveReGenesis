package arg.content.deterra;

import arg.content.GenesisPlanets;
import arg.type.GenesisSectorPreset;
import mindustry.type.SectorPreset;

public class DeterraSectorPresets {
    public static GenesisSectorPreset landfall;

    public static void load(){
        landfall = new GenesisSectorPreset("landfall", GenesisPlanets.deterra, 2){{
           alwaysUnlocked = true;
           difficulty = 1;
           overrideLaunchDefaults = true;
        }};
    }
}