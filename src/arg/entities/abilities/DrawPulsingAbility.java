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

//it draws scary pulsing TextureRegion
//Only support y offset for a very mysterious reason
public class DrawPulsingAbility extends Ability{
    public float y, mag, interval;
    public TextureRegion pulseRegion;
    public String pulseSuffix = "-pulse";
    public float layer = -1;

    protected float counter;

    public DrawPulsingAbility(String pulseSuffix, float x, float y, float mag, float interval){
        this.pulseSuffix = pulseSuffix;
        //this.x = no lol
        this.y = y;
        this.mag = mag;
        this.interval = interval;
        display = false;
    }

    public DrawPulsingAbility(){
        display = false;
    }

    @Override
    public void update(Unit unit){
        super.update(unit);
        
        counter+=Time.delta;
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