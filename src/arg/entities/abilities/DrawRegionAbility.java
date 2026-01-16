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

//it draws TextureRegion. No positioning support
public class DrawRegionAbility extends Ability{
    public TextureRegion region;
    public String regionSuffix = "-region";
    public float layer = -1;
    
    public DrawRegionAbility(String regionSuffix){
        this.regionSuffix = regionSuffix;
        display = false;
    }

    public DrawRegionAbility(){
        display = false;
    }
    
    @Override
    public void draw(Unit unit){
        if(region == null){
            region = Core.atlas.find(unit.type.name + regionSuffix, unit.type.region);
        }
        float pz = Draw.z();
        if(layer > 0) Draw.z(layer);

        Draw.rect(region, unit.x, unit.y, unit.rotation - 90f);
        
        Draw.z(pz);
    }
}