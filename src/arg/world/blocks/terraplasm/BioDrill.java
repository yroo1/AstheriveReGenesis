package arg.world.blocks.terraplasm;

import arc.Core;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.g2d.TextureRegion;
import arc.math.*;
import arc.util.*;
import arc.util.io.*;
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
import java.util.Random;
import arg.graphics.*;

import static mindustry.Vars.*;

public class BioDrill extends BioBlock {
    protected Item returnItem;
    protected int returnCount;
    
    public BioDrill(String name){
        super(name);
        update=true;
        isRoot=false;
    }

    protected void countOre(Tile tile){
        returnItem = null;
        returnCount = 0;
        
        for(Tile other : tile.getLinkedTilesAs(this, tempTiles)){
            if(canMine(other)){
                returnCount++;
                returnItem = other.drop();
            }
        }
    }
    
    public boolean canMine(Tile other){
        return other.drop()!=null;
    }
    
    public class BioDrillBuild extends BioBuilding {
        public int drillProgress = 0;
        
        @Override
        public void updatePulse() {
            //TODO rework back to this->pulse
            //ONLY WORK FOR 2x2, smh my head
            if (true) {
                drillProgress++;
                for(int i=0;i<4;i++){
                    for(int j=0;j<=1;j++){
                        Building adj;
                        if(i==0){
                            adj = tile.nearby(Geometry.d4(i).x*2,Geometry.d4(i).y*2+j).build;
                        } else if(i==1) {
                            adj = tile.nearby(Geometry.d4(i).x*2+j,Geometry.d4(i).y*2).build;
                        } else if(i==2) {
                            adj = tile.nearby(Geometry.d4(i).x,Geometry.d4(i).y+j).build;
                        } else {
                            adj = tile.nearby(Geometry.d4(i).x+j,Geometry.d4(i).y).build;
                        }
                        if (adj instanceof BioBuilding adjbuild) {
                            //smh
                        }
                    }
                }
                countOre(tile);
                if(pulseSource != null && drillProgress >= 14-returnCount && returnItem != null) {
                    drillProgress = 0;
                    Building target = pulseSource.build;
                    if(target != null && target instanceof BioBuilding && target.acceptItem(this, returnItem)){
                        target.handleItem(this, returnItem);
                    }
                }
            }
        }
        @Override
        public void draw(){
            drawPulse(block.region,drawPulseScale);
        }
        @Override
        public void write(Writes write){
            super.write(write);
            write.i(drillProgress);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            drillProgress=read.i();
        }
    }
 }     
