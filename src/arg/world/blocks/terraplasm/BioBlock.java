package arg.world.blocks.terraplasm;

import arc.Core;
import arc.audio.*;
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
import mindustry.entities.effect.*;
import mindustry.gen.*;
import mindustry.world.meta.*;
import java.util.ArrayList;
import java.util.Random;
import arg.content.*;

import static mindustry.Vars.*;

public class BioBlock extends Block {
    public boolean isRoot=false;
    public float pulseScale=0.5f;
    
    //Parameters, to be overriden by data patches
    public boolean allowRoot=false;
    public boolean allowDrill=false;
    public boolean allowBridge=false;
    public boolean allowEye=false;
    public float eyeRate=0.01f;
    public boolean allowTurretSpitterRand=false;
    public float turretSpitterRandRate=0.01f;
    
    public BioBlock(String name){
        super(name);
        update=true;
        rebuildable = false;
        drawTeamOverlay = false;
        destroySound = Sounds.loopSpray;
        buildVisibility = BuildVisibility.sandboxOnly;
    }
    public class BioBuilding extends Building {
        public Tile pulseSource=null;
        
        public float pulseProgress=0;
        public int biopulse=0;
        public float pulseTimer=0;
        public float resetPulseTimer=0;
        public float deathTimer=0;
        public float deathTimerLimit=180f;
        public boolean pulsed=false;
        public boolean fullyGrown=false;
        public float growProgress=-1;

        public ArrayList<Integer> possibleGrowDir = new ArrayList<>();
        public float drawPulseScale=0;
        public Tile nearbyTile(int rotation, Tile tile){
            //terrible mess, but if it work it work
            return switch(rotation){
                case 0 -> world.tile(tile.x + 1, tile.y);
                case 1 -> world.tile(tile.x, tile.y + 1);
                case 2 -> world.tile(tile.x - 1, tile.y);
                case 3 -> world.tile(tile.x, tile.y - 1);
                default -> null;
            };
        }
        @Override
        public void updateTile() {
            //TODO try syncing invididually?
            if (biopulse>0&&!pulsed&&fullyGrown){
                growProgress=0f;
                deathTimer=0f;
                if (pulseTimer<4f) {
                    pulseTimer+=delta();
                } else {
                    updatePulse();
                    pulseTimer=0;
                    biopulse=0;
                    pulsed=true;
                    drawPulseScale=pulseScale;
                }
            }
            if (pulsed) {
                if (resetPulseTimer<25f) {
                    resetPulseTimer+=delta();
                } else {
                    resetPulseTimer=0;
                    pulsed=false;
                }
            }
            if (biopulse>=0&&deathTimer<deathTimerLimit){
                deathTimer+=delta();
            }
            if (deathTimer>=deathTimerLimit){
                this.damage(4);
            }
            
            if (drawPulseScale>0.01f) {
                drawPulseScale*=0.85f;
            }

            if(!fullyGrown){
                if(!isRoot) {
                    growProgress*=0.95f;
                } else {
                    growProgress*=0.7f; //root grows faster
                }
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
            boolean pulseEnd=true;
            int neartileCount=0;
            if (true) {
                possibleGrowDir.clear();
                for (int i=0;i<4;i++) {
                    Building nearroot = tile.nearbyBuild(i);
                    Tile neartile = tile.nearby(i);
                    if (nearroot instanceof BioTurret.BioTurretBuild nearbuild) {
                        if (!nearbuild.pulsed) {                      
                            nearbuild.biopulse=Math.max(nearbuild.biopulse,biopulse-1);
                            pulseEnd=false;
                        }
                    }
                    if (nearroot instanceof BioBuilding nearbuild) {
                        if (!nearbuild.pulsed) {                      
                            nearbuild.biopulse=Math.max(nearbuild.biopulse,biopulse-1);
                            nearbuild.pulseSource=tile;
                            pulseEnd=false;
                        }
                    } else if (!neartile.solid()) {
                        /*
                        int nearneaarcount=0;
                        for (int i2=0;i2<4;i2++) 
                            Tile nearneartile = nearbyTile() {
                                    Drawf.selected
                                }
                         i2,neartile); //stoopid variable naming lol
                            if (nearneartile!=null) {
                                if (nearneartile.block() != Blocks.air) {
                                    nearnearcount++;
                                }
                            }
                        }
                        if (nearnearcount<2) {
                            possibleGrowDir.add(i);
                        }
                        */
                        neartileCount=0;
                        for(int xm = -1;xm<=1;xm++){
                            for(int ym = -1;ym<=1;ym++){
                                Tile other = tile.nearby(xm+Geometry.d4(i).x,ym+Geometry.d4(i).y);
                                if(other.build!=null){
                                    if(other.build.block==block) {
                                        neartileCount++;
                                    }
                                }
                            }
                        }
                        if(neartileCount<4&&Build.validPlace(this.block, team, neartile.x, neartile.y, 0)){
                            possibleGrowDir.add(i);
                        }
                    }
                }
                Random random = new Random();
                if (isRoot&&possibleGrowDir.size()>0&&biopulse>1){
                    growRoot();
                }
            }
        }
        public void growRoot() {
            Random random = new Random();
            int randomIndex = random.nextInt(possibleGrowDir.size());
            int growDir = possibleGrowDir.get(randomIndex);
            Tile targetTile = tile.nearby(growDir);
            targetTile.setBlock(block,team);
        }
        public void drawPulse(TextureRegion sprite,float scale) {
            scale+=1f+growProgress;
            if (scale>0.01f) {
                float sx=x-scale*0.5f;
                float sy=y-scale*0.5f;
                Draw.scl(scale,scale);
                Draw.rect(sprite,sx,sy);
            } else {
                Draw.rect(sprite,x,y,rotation);
            }
        }
        @Override
        public void draw() {
            drawPulse(region,drawPulseScale);
            Draw.scl(1,1);
        }
        public void onDestroyed(){
            splashLiquid(GenesisLiquids.biomass,40*size);
        }
        @Override
        public void write(Writes write){
            super.write(write);
            write.i(biopulse);
            write.f(pulseTimer);
            write.f(pulseProgress);
            write.f(resetPulseTimer);
            write.f(deathTimer);
            write.bool(pulsed);
            write.bool(fullyGrown);
            write.f(growProgress);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            biopulse=read.i();
            pulseTimer=read.f();
            pulseProgress=read.f();
            resetPulseTimer=read.f();
            deathTimer=read.f();
            pulsed=read.bool();
            fullyGrown=read.bool();
            growProgress=read.f();
        }
    }
}
