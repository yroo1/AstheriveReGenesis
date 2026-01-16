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

//it draws scary pulsing TextureRegion
//Only support y offset for a very mysterious reason
public class HeartAbility extends Ability{
    public float y, mag, bpm;
    public TextureRegion pulseRegion;
    public String pulseSuffix = "-pulse";
    public float layer = -1;
    public int heartPower = 24;

    protected float pulseTimer;

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
            updatePulse()
        }
    }
    public void updatePulse(){
        for(int xm = -2;xm<=2;xm++){
            for(int ym = -2;ym<=2;ym++){
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
    }
    @Override
    public void draw(Unit unit){
        if(pulseRegion == null){
            pulseRegion = Core.atlas.find(unit.type.name + pulseSuffix, unit.type.region);
        }
        float pz = Draw.z();
        if(layer > 0) Draw.z(layer);
        
        Draw.rect(pulseRegion, unit.x+Mathf.cosDeg(unit.rotation - 90f)*y, unit.y+Mathf.sinDeg(unit.rotation - 90f)*y, unit.rotation - 90f);
            
        Draw.z(pz);
    }
}