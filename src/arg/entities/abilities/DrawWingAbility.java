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

//it draws wing, nothing else.
public class DrawWingAbility extends Ability{
    public float x, y, spread, mag, interval;
    public TextureRegion wingRegionL,wingRegionR;
    public String wingSuffix = "-wing";
    public float layer = -1;

    protected float counter;

    public DrawWingAbility(float x, float y, float spread, float mag, float interval){
        this.x = x;
        this.y = y;
        this.spread = spread;
        this.mag = mag;
        this.interval = interval;
        display = false;
    }

    public DrawWingAbility(){
        display = false;
    }

    @Override
    public void update(Unit unit){
        super.update(unit);
        
        counter+=Time.delta*Mathf.PI/interval;
    }
    @Override
    public void draw(Unit unit){
        if(wingRegionL == null || wingRegionR == null){
            wingRegionL = Core.atlas.find(unit.type.name + wingSuffix + "-l", unit.type.region);
            wingRegionR = Core.atlas.find(unit.type.name + wingSuffix + "-r", unit.type.region);
        }
        float pz = Draw.z();
        if(layer > 0) Draw.z(layer);

        Draw.rect(wingRegionL, unit.x+Mathf.cosDeg(unit.rotation - 90f)*spread+x, unit.y+Mathf.sinDeg(unit.rotation - 90f)*spread+y, unit.rotation + 90f + Mathf.sin(counter)*mag);
        Draw.rect(wingRegionR, unit.x+Mathf.cosDeg(unit.rotation + 90f)*spread+x, unit.y+Mathf.sinDeg(unit.rotation + 90f)*spread+y, unit.rotation + 90f + Mathf.sin(counter)*mag);
            
        Draw.z(pz);
    }
}