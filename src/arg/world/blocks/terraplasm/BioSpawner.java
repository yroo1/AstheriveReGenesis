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
import mindustry.world.meta.*;
import mindustry.gen.*;
import mindustry.type.*;
import java.util.Random;
import arg.graphics.*;
import arg.content.GenesisFx;
import mindustry.entities.Effect;

import static mindustry.Vars.*;

public class BioSpawner extends BioBlock {
    public UnitType unitType;
    public TextureRegion bubbleRegion;
    public Effect spawnEffect=GenesisFx.bulbPop;
    
    public float wscl = 10f, wmag = 1.2f, wtscl = 1f, wmag2 = 1.5f;
    
    public int pulseToSpawn=12;
    public BioSpawner(String name){
        super(name);
        update=true;
        isRoot=false;
    }
    
    @Override
    public void load(){
        super.load();
        bubbleRegion = Core.atlas.find(name+"-bubble");
    }
    
    public class BioSpawnerBuild extends BioBuilding {
        public int spawnProgress = 0;
        
        @Override
        public void updatePulse(){
            if (true) {
                spawnProgress++;
                if(spawnProgress >= pulseToSpawn) {
                    spawnProgress = 0;
                    Unit unit = unitType.create(team);
                    unit.set(x, y);
                    unit.rotation = 90f;
                    unit.add();
                    spawnEffect.at(x,y);
                }
            }
        }
        @Override
        public void draw(){
            drawPulse(block.region,drawPulseScale);
            //copied from WobbleProp lol
            float bubbleScale = 0.2f + spawnProgress / (float)pulseToSpawn;
            Draw.rectv(bubbleRegion, tile.worldx(), tile.worldy(), region.width * region.scl()*(1+drawPulseScale)*bubbleScale, region.height * region.scl()*(1+drawPulseScale)*bubbleScale, 0, vec -> vec.add(
            Mathf.sin(vec.y*3 + Time.time, wscl, wmag) + Mathf.sin(vec.x*3 - Time.time, 70 * wtscl, 0.8f * wmag2),
            Mathf.cos(vec.x*3 + Time.time + 8, wscl + 6f, wmag * 1.1f) + Mathf.sin(vec.y*3 - Time.time, 50 * wtscl, 0.2f * wmag2)
            ));
        }
        @Override
        public void write(Writes write){
            super.write(write);
            write.i(spawnProgress);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            spawnProgress=read.i();
        }
    }
 }     
