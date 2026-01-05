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
import arg.content.*;

import static mindustry.Vars.*;

public class BioEye extends BioBlock {
    public float range = 200;
    public TextureRegion eyeRegion;
    public BioEye(String name){
        super(name);
        update=true;
        isRoot=false;
    }

    @Override
    public void load(){
        super.load();
        eyeRegion = Core.atlas.find(name+"-pupil");
    }
    
    public class BioEyeBuild extends BioBuilding {
        public float eyeX = 0;
        public float eyeY = 0;
        public float tx = x, ty = y;
        public @Nullable Unit target;
        @Override
        public void updatePulse(){
            if (true) {
                //no
            }
        }
        @Override
        public void updateTile(){
            super.updateTile();
            target=Units.closestEnemy(team, x, y, range, u -> true);
            if(target != null){
                tx = target.x();
                ty = target.y();
                target.apply(GenesisStatusEffects.seen,30f);
            }
            float mag = Mathf.dst(x,y,tx,ty);
            eyeX = Mathf.lerp(eyeX,(tx-x)/mag*3,0.1f);
            eyeY = Mathf.lerp(eyeY,(ty-y)/mag*3,0.1f);
        }
        @Override
        public void draw(){
            drawPulse(block.region,drawPulseScale);
            Draw.rect(eyeRegion,x+eyeX,y+eyeY);
        }
        @Override
        public void write(Writes write){
            super.write(write);
            write.f(eyeX);
            write.f(eyeY);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            eyeX=read.f();
            eyeY=read.f();
        }
    }
 }     
