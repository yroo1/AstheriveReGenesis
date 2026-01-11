package arg.world.blocks.terraplasm;

import arc.Core;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.g2d.TextureRegion;
import arc.math.*;
import arc.util.*;
import arc.math.geom.*;
import mindustry.world.blocks.defense.*;
import mindustry.gen.Building;
import mindustry.graphics.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.Tile;
import mindustry.graphics.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.type.*;
import mindustry.gen.*;
import mindustry.world.meta.*;
import arg.content.terraplasm.Terraplasm;

import static mindustry.Vars.*;

public class BioHeart extends BioBlock {
    //modify with data patch for seizure
    public float heartBpm=80;
    public int heartPower=32;
    public BioHeart(String name){
        super(name);
        priority = TargetPriority.core;
        hasItems = true;
        itemCapacity = 20;
        unloadable = false;
    }
    public class BioHeartBuild extends BioBuilding {        
        @Override
        public void updateTile() {
            if (fullyGrown){
                growProgress=0f;
                if (pulseTimer<3600/heartBpm) {
                    pulseTimer+=delta();
                } else {
                    updatePulse();
                    pulseTimer=0;
                    drawPulseScale=pulseScale;
                    if(allowRoot)growRoots();
                }
                if (drawPulseScale>0.01f) {
                    drawPulseScale*=0.9;
                }
            }
            if(!fullyGrown){
                growProgress*=0.95;
                pulsed=true; //prevents from getting pulse when still growing
                if(growProgress>-0.05){
                    growProgress=0;
                    fullyGrown=true;
                    pulsed=false;
                }
            }
        }
        public void updatePulse() {
            //TODO rework back to this->pulse
            if (true) {
                for(int i=0;i<4;i++){
                    for(int j=-1;j<=1;j++){
                        Building adj;
                        if(i==0||i==2){
                            adj = tile.nearby(Geometry.d4(i).x*2,Geometry.d4(i).y*2+j).build;
                        } else {
                            adj = tile.nearby(Geometry.d4(i).x*2+j,Geometry.d4(i).y*2).build;
                        }
                        if (adj instanceof BioBuilding adjbuild) {
                            if (!adjbuild.pulsed) {                        
                                adjbuild.biopulse=Math.max(adjbuild.biopulse,heartPower);
                                adjbuild.pulseSource=tile;
                            }
                        }
                    }
                }
            }
        }
        public void growRoots(){
            //only for 3x3 block smh
            //well who cares lmao it works
            for(int i=0;i<4;i++){
                for(int j=-1;j<=1;j++){
                    Tile adj;
                    if(i==0||i==2){
                        adj = tile.nearby(Geometry.d4(i).x*2,Geometry.d4(i).y*2+j);
                    } else {
                        adj = tile.nearby(Geometry.d4(i).x*2+j,Geometry.d4(i).y*2);
                    }
                    if(adj.build==null) adj.setBlock(Terraplasm.root,team);
                }
            }
        }
        @Override
        public void draw(){
            Draw.z(Layer.block+0.01f); //just slightly above...
            drawPulse(block.region,drawPulseScale);
        }
        @Override
        public int acceptStack(Item item, int amount, Teamc source){
            return 0;
        }

        @Override
        public boolean acceptItem(Building source, Item item){
            return true; //this guy always accept items, if its full item will be accepted but not added
        }

        @Override
        public void handleItem(Building source, Item item){
            if(items.get(item) < itemCapacity) items.add(item, 1);
        }

        @Override
        public int removeStack(Item item, int amount){
            int result = super.removeStack(item, amount);
            return result;
        }
    }
}

