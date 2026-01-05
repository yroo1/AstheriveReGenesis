package arg.world.blocks.production;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.units.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.logic.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;
import mindustry.world.blocks.production.Drill;
import arg.world.blocks.environment.OreCluster;
import arg.content.deterra.blocks.DeterraEnv;

import static mindustry.Vars.*;

//drill that can only work when its fully covered by ores AND with specific hardcoded floor (Horrendous, TODO: dynamic)
public class ClusterDrill extends Drill{
    public ClusterDrill(String name){
        super(name);
        update = true;
        solid = true;
        group = BlockGroup.drills;
        hasLiquids = true;
        liquidCapacity = 5f;
        hasItems = true;
        ambientSound = Sounds.loopDrill;
        ambientSoundVolume = 0.018f;
        //sure anuke drills do work in space
        envEnabled |= Env.space;
        flags = EnumSet.of(BlockFlag.drill);
    }
    
    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        countOre(tile);
        if(isMultiblock()){
            for(Tile other : tile.getLinkedTilesAs(this, tempTiles)){
                if(canMine(other)){
                    if(returnCount>=size*size){
                        return true;
                    }
                }
            }
            return false;
        }else{
            return canMine(tile);
        }
    }
    @Override
    public boolean canMine(Tile tile){
        if(tile == null || tile.block().isStatic()) return false;
        Item drops = tile.drop();
        if(tile.floor() == DeterraEnv.eonstoneQuartzCluster||tile.floor() == DeterraEnv.eonstoneMagnetiteCluster){
            return drops != null && drops.hardness <= tier && (blockedItems == null || !blockedItems.contains(drops));
        } else {return false;}
    }
}
