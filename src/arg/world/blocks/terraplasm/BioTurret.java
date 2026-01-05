package arg.world.blocks.terraplasm;

import arc.Core;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.g2d.TextureRegion;
import arc.math.*;
import arc.util.*;
import arc.util.io.*;
import arc.math.geom.*;
import mindustry.world.blocks.defense.turrets.*;
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
import arc.struct.*;
import mindustry.entities.bullet.*;
import mindustry.logic.*;

import static mindustry.Vars.*;

public class BioTurret extends Turret{
    public BulletType shootType;
    public boolean isRoot=false;
    public float pulseScale=0.5f;
    
    public BioTurret(String name){
        super(name);
        update=true;
        rebuildable = false;
        drawTeamOverlay = false;
        destroySound = Sounds.loopSpray;
        unitFilter = u -> u.getDuration(GenesisStatusEffects.seen)>1;
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(Stat.ammo, StatValues.ammo(ObjectMap.of(this, shootType)));
    }

    public void limitRange(float margin){
        limitRange(shootType, margin);
    }

    public class BioTurretBuild extends TurretBuild{
        public float pulseProgress=0;
        public int biopulse=0;
        public float pulseTimer=0;
        public float resetPulseTimer=0;
        public float deathTimer=0;
        public float deathTimerLimit=180f;
        public boolean pulsed=false;
        public boolean fullyGrown=false;
        public float growProgress=-1;
        public float drawPulseScale=0;
        
        @Override
        public void updateTile(){
            super.updateTile();
            if (biopulse>0&&!pulsed&&fullyGrown){
                growProgress=0f;
                deathTimer=0f;
                if (pulseTimer<4f) {
                    pulseTimer+=delta();
                } else {
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

        public void onDestroyed(){
            splashLiquid(GenesisLiquids.biomass,40*size);
        }
        @Override
        public double sense(LAccess sensor){
            return switch(sensor){
                case ammo -> power.status;
                case ammoCapacity -> 1;
                default -> super.sense(sensor);
            };
        }

        @Override
        public BulletType useAmmo(){
            //nothing used directly
            return shootType;
        }

        @Override
        public boolean hasAmmo(){
            //you can always rotate, but never shoot if there's no power
            return true;
        }

        @Override
        public BulletType peekAmmo(){
            return shootType;
        }
    }
}