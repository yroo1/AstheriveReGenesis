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
    public float x, y, mag, interval;
    public TextureRegion wingRegion;
    public float layer = -1;

    protected float counter;

    public DrawWingAbility(float x, float y, TextureRegion wingRegion, float mag, float interval){
        this.x = x;
        this.y = y;
        this.wingRegion = wingRegion;
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
        float pz = Draw.z();
        if(layer > 0) Draw.z(layer);

        Draw.rect(wingRegion, unit.x-x, unit.y+y, unit.rotation - 90f + Mathf.sin(counter)*mag);
        Draw.rect(wingRegion, unit.x+x, unit.y+y, unit.rotation - 90f + Mathf.sin(counter)*mag);
            
        Draw.z(pz);
    }
}