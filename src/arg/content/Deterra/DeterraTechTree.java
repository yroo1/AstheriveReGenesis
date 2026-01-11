package arg.content.deterra;

import arc.struct.*;
import arg.content.GenesisPlanets;
import arg.content.deterra.blocks.*;
import arg.content.deterra.*;
import mindustry.game.Objectives;
import mindustry.type.Item;

import static arg.content.deterra.DeterraSectorPresets.*;
import static mindustry.Vars.content;
import static mindustry.content.TechTree.*;

public class DeterraTechTree {
    public static void load(){
        GenesisPlanets.deterra.techTree = nodeRoot("Deterra", DeterraStorage.coreProtocol, () -> {
            node(DeterraItems.quartz, Seq.with(new Objectives.Produce(DeterraItems.quartz)), () -> {
                node(DeterraItems.magnetite, Seq.with(new Objectives.Produce(DeterraItems.magnetite)), () ->{
                    node(DeterraItems.carbon, Seq.with(new Objectives.Produce(DeterraItems.carbon)), () ->{
                  
                    });
                    node(DeterraItems.polterite, Seq.with(new Objectives.Produce(DeterraItems.polterite)), () ->{
                  
                    });
                });
            });
            node(DeterraDistribution.itemTube, () -> {
                node(DeterraDistribution.splitter, () -> {
                   node(DeterraDistribution.filter);
                   node(DeterraDistribution.itemGate);
                });
                node(DeterraDistribution.itemOverpass);
                node(DeterraDistribution.itemLauncher,Seq.with(new Objectives.SectorComplete(woodland)),() -> {
                    
                });
            });
            node(DeterraProduction.jaggedDrill, () -> {
                node(DeterraProduction.cliffBore,Seq.with(new Objectives.SectorComplete(woodland)),() -> {
                        
                });
            });
            node(DeterraProduction.polteritePress,Seq.with(new Objectives.SectorComplete(woodland)), () -> {
            });
            node(DeterraTurrets.fracture,Seq.with(new Objectives.OnSector(woodland)), () -> {
                node(DeterraTurrets.frail, () -> {
                });
            });
            node(DeterraPower.relay, () -> {
                node(DeterraPower.windTurbine, () -> {
                    node(DeterraPower.ventTurbine,Seq.with(new Objectives.SectorComplete(landfall)), () -> {
                        
                    });
                });
            });
            node(DeterraUnits.mechAssembler, () -> {
                node(DeterraUnitTypes.saber, () -> {
                
                });
                node(DeterraUnits.boatAssembler,Seq.with(new Objectives.SectorComplete(woodland)), () -> {
                    node(DeterraUnitTypes.tide, () -> {
                
                    });
                });
            });
            node(DeterraSectorPresets.landfall, () -> {
                node(DeterraSectorPresets.woodland, Seq.with(new Objectives.SectorComplete(landfall)), () -> {
                    node(DeterraSectorPresets.remnants, Seq.with(new Objectives.SectorComplete(woodland),Objectives.Research(DeterraUnits.boatAssembler),Objectives.Research(DeterraDistribution.itemLauncher)), () -> {
                    });
                });
            });
        });
    }
}