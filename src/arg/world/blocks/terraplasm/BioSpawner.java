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

import static mindustry.Vars.*;

public class BioSpawner extends BioBlock {
    public UnitType unitType;
    public BioSpawner(String name){
        super(name);
        update=true;
        isRoot=false;
    }
    
    public class BioSpawnerBuild extends BioBuilding {
        public int spawnProgress = 0;
        
        @Override
        public void updatePulse(){
            if (true) {
                spawnProgress++;
                if(spawnProgress >= 10) {
                    spawnProgress = 0;
                    Unit unit = unitType.create(team);
                    unit.set(x, y);
                    unit.rotation = 90f;
                    unit.add();
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
            write.i(spawnProgress);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            spawnProgress=read.i();
        }
    }
 }     
