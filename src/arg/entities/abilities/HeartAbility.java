package arg.entities.abilities;

import arc.graphics.*;
import arc.math.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.entities.abilities.*;
import arc.*;
import arc.graphics.g2d.*;
import arc.scene.ui.layout.*;
import mindustry.graphics.*;
import arg.world.blocks.terraplasm.*;
import arg.content.terraplasm.*;
import mindustry.world.*;

import static mindustry.Vars.*;

//it draws scary pulsing TextureRegion
//Only support y offset for a very mysterious reason
public class HeartAbility extends Ability{
    public float y, mag, bpm;
    public TextureRegion pulseRegion;
    public String pulseSuffix = "-pulse";
    public float layer = -1;
    public int heartPower = 20;

    protected float pulseTimer;
    protected float tilesize = 8; //idk why its here but it stays
    protected float drawPulseScale = 0;

    public HeartAbility(String pulseSuffix, float x, float y, float mag, float bpm){
        this.pulseSuffix = pulseSuffix;
        //this.x = no lol
        this.y = y;
        this.mag = mag;
        this.bpm = bpm;
        this.pulseTimer = 3600/bpm;
        display = false;
    }

    public HeartAbility(){
        display = false;
    }

    @Override
    public void update(Unit unit){
        super.update(unit);
        
        pulseTimer-=Time.delta;
        if(pulseTimer<=0){
            pulseTimer = 3600/bpm;
            updatePulse(unit);
        }
        if (drawPulseScale>0.01f) {
            drawPulseScale*=0.85f;
        }
    }
    public void growRoot(Unit unit){
        //this only grow a single root that grows more roots
        Tile rootTile = world.tile((int)(Math.round(unit.x/tilesize)),(int)(Math.round(unit.y/tilesize)));
        if(Build.validPlace(Terraplasm.root, unit.team, rootTile.x, rootTile.y, 0)){
            rootTile.setBlock(Terraplasm.root,unit.team);
            if(rootTile.build instanceof BioBlock.BioBuilding biobuild)biobuild.monarch=true;
        }
    }
    public void updatePulse(Unit unit){
        growRoot(unit);
        for(int xm = -3;xm<=3;xm++){
            for(int ym = -3;ym<=3;ym++){
                Tile other = world.tile((int)(Math.round(unit.x/tilesize))+xm,(int)(Math.round(unit.y/tilesize))+ym);
                if(other.build!=null){
                    if (other.build instanceof BioBlock.BioBuilding otherbuild) {
                        if (!otherbuild.pulsed) {                        
                            otherbuild.biopulse=Math.max(otherbuild.biopulse,heartPower);
                        }
                    }
                }
            }
        }
        drawPulseScale = 0.5f;
    }
    @Override
    public void draw(Unit unit){
        if(pulseRegion == null){
            pulseRegion = Core.atlas.find(unit.type.name + pulseSuffix, unit.type.region);
        }
        float pz = Draw.z();
        if(layer > 0) Draw.z(layer);
        
        Draw.scl(1f+drawPulseScale,1f+drawPulseScale);
        Draw.rect(pulseRegion, unit.x+Mathf.cosDeg(unit.rotation - 90f)*y, unit.y+Mathf.sinDeg(unit.rotation - 90f)*y, unit.rotation - 90f);
        Draw.scl(1,1);
            
        Draw.z(pz);
    }
}