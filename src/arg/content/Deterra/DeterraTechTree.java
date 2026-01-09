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
            });
            node(DeterraProduction.jaggedDrill, () -> {
                node(DeterraProduction.cliffBore,Seq.with(new Objectives.OnSector(woodland)),() -> {
                        
                });
            });
            node(DeterraProduction.polteritePress,Seq.with(new Objectives.OnSector(woodland)), () -> {
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
            });
            node(DeterraSectorPresets.landfall, () -> {
                node(DeterraSectorPresets.woodland, Seq.with(new Objectives.SectorComplete(landfall)), () -> {
                });
            });
        });
    }
}