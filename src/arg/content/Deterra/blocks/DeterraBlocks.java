package arg.content.deterra.blocks;
import arg.content.terraplasm.*;

public class DeterraBlocks{
    public static void load(){
        DeterraEnv.load();
        DeterraTurrets.load();
        DeterraProduction.load();
        DeterraDistribution.load();
        DeterraLiquid.load();
        DeterraDefense.load();
        DeterraPower.load();
        DeterraStorage.load();
        DeterraUnits.load();
        Terraplasm.load();
    }
}
